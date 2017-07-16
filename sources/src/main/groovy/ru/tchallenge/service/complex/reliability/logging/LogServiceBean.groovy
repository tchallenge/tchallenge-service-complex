package ru.tchallenge.service.complex.reliability.logging

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.logging.LogLevel

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.logging.LogFactory

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@ServiceComponent
class LogServiceBean extends GenericService implements LogService {

    @Autowired
    ObjectMapper objectMapper

    @Override
    void log(LogRecord record) {
        try {
            logRecord(record)
        } catch (Throwable throwable) {
            logOwnError(throwable)
        }
    }

    private void logRecord(LogRecord record) {
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
        def result = new StringBuilder(message)
        if (payload) {
            def payloadSerialized = objectMapper.writeValueAsString(payload)
            result.append(": $payloadSerialized")
        }
        def joinedAttributes = attributes.join(", ")
        if (joinedAttributes) {
            result.append(" ($joinedAttributes)")
        }
        result.toString()
    }

    private Collection<String> getAttributes() {
        def result = []
        if (correlation.present) {
            def correlationId = correlation.get().id
            result.add("correlation: $correlationId")
        }
        if (authentication.present) {
            def accountId = authentication.get().account.id
            result.add("account: $accountId")
            def logonAt = authentication.get().token.createdAt
            result.add("logon: $logonAt")
        }
        result
    }

    private static void logOwnError(Throwable throwable) {
        LogFactory
                .getLog(LogServiceBean.class.name)
                .fatal('Logging attempt has failed', throwable)
    }
}
