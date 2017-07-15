package ru.tchallenge.service.complex.reliability.logging

import groovy.transform.CompileStatic

import org.springframework.beans.factory.BeanCreationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.logging.LogLevel

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.reliability.correlation.CorrelationContext
import ru.tchallenge.service.complex.reliability.correlation.CorrelationInfo
import ru.tchallenge.service.complex.security.authentication.AuthenticationContext
import ru.tchallenge.service.complex.security.authentication.AuthenticationInfo

@CompileStatic
@ServiceComponent
class LogServiceBean extends GenericService implements LogService {

    @Autowired
    AuthenticationContext authenticationContext

    @Autowired
    CorrelationContext correlationContext

    @Autowired
    ObjectMapper objectMapper

    @Override
    void log(LogRecord record) {
        try {
            logRecord(record)
        } catch (Throwable throwable) {
            ownLog.fatal("Logging failed", throwable)
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
        def attributes = attributes()
        if (!attributes.empty) {
            def joinedAttributes = attributes.join(", ")
            result.append(" ($joinedAttributes)")
        }
        return result
    }

    private Collection<String> attributes() {
        def result = []
        def correlation = correlationIfAny()
        if (correlation) {
            result.add("correlation: $correlation.id")
        }
        def authentication = authenticationIfAny()
        if (authentication) {
            result.add("account: $authentication.account.id")
            result.add("logon: $authentication.token.createdAt")
        }
        return result
    }

    private AuthenticationInfo authenticationIfAny() {
        try {
            authenticationContext.authentication.orElse(null)
        } catch (BeanCreationException exception) {
            ownLog.trace("No authentication is available", exception)
            null
        }
    }

    private CorrelationInfo correlationIfAny() {
        try {
            correlationContext.correlation.orElse(null)
        } catch (BeanCreationException exception) {
            ownLog.trace("No correlation is available", exception)
            null
        }
    }

    private Log getOwnLog() {
        LogFactory.getLog(this.class.name)
    }
}
