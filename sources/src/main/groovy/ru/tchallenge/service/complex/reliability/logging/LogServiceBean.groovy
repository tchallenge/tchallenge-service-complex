package ru.tchallenge.service.complex.reliability.logging

import groovy.transform.CompileStatic

import org.springframework.beans.factory.BeanCreationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.logging.LogLevel

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.logging.LogFactory

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.reliability.correlation.CorrelationContext
import ru.tchallenge.service.complex.reliability.correlation.CorrelationInfo

@CompileStatic
@ServiceComponent
class LogServiceBean extends GenericService implements LogService {

    @Autowired
    CorrelationContext correlationContext

    @Autowired
    ObjectMapper objectMapper

    @Override
    void log(LogRecord record) {
        def log = LogFactory.getLog(record.descriptor)
        def level = record.level
        def message = record.message
        def payload = record.payload
        def throwable = record.throwable
        if (level == LogLevel.TRACE && log.traceEnabled) {
            log.trace(serialize(message, payload), throwable)
        } else if (level == LogLevel.DEBUG && log.debugEnabled) {
            log.debug(serialize(message, payload), throwable)
        } else if (level == LogLevel.INFO && log.infoEnabled) {
            log.info(serialize(message, payload), throwable)
        } else if (level == LogLevel.WARN && log.warnEnabled) {
            log.warn(serialize(message, payload), throwable)
        } else if (level == LogLevel.ERROR && log.errorEnabled) {
            log.error(serialize(message, payload), throwable)
        } else if (level == LogLevel.FATAL && log.fatalEnabled) {
            log.fatal(serialize(message, payload), throwable)
        }
    }

    private String serialize(String message, Object payload) {
        def correlation = correlationIfAny()
        def correlationIdSerializedBlock = correlation ? "$correlation.id | " : ""
        def correlationSerialized = json(correlation)
        def correlationSerializedBlock = correlation ? ", (correlation: $correlationSerialized)" : ""
        def payloadSerialized = json(payload)
        def payloadSerializedBlock = payload ? ": $payloadSerialized" : ""
        return correlationIdSerializedBlock + message + payloadSerializedBlock + correlationSerializedBlock
    }

    private CorrelationInfo correlationIfAny() {
        try {
            return correlationContext.correlation
        } catch (BeanCreationException exception) {
            def log = LogFactory.getLog(this.class.name)
            log.trace("No correlation is available ($exception.message)")
            return null
        }
    }

    private String json(Object object) {
        return object ? objectMapper.writeValueAsString(object) : null
    }
}
