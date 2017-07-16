package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

import java.time.Instant
import java.util.function.Function
import javax.annotation.PostConstruct

import org.springframework.beans.factory.BeanCreationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.logging.LogLevel

import ru.tchallenge.service.complex.reliability.correlation.CorrelationContext
import ru.tchallenge.service.complex.reliability.correlation.CorrelationInfo
import ru.tchallenge.service.complex.reliability.logging.LogRecord
import ru.tchallenge.service.complex.reliability.logging.LogService
import ru.tchallenge.service.complex.security.authentication.AuthenticationContext
import ru.tchallenge.service.complex.security.authentication.AuthenticationInfo
import ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals

@CompileStatic
abstract class GenericComponent {

    protected static Integer flag(Boolean value) {
        Foundamentals.flag(value)
    }

    protected static boolean flag(Integer value) {
        Foundamentals.flag(value)
    }

    protected static Instant instant(String iso) {
        Foundamentals.instant(iso)
    }

    protected static <R, T> Collection<R> mapCollection(Collection<T> collection, Function<T, R> mapper) {
        Foundamentals.mapCollection(collection, mapper)
    }

    protected static Instant getNow() {
        Foundamentals.now
    }

    protected static String getUuid() {
        Foundamentals.uuid
    }

    @Autowired
    AuthenticationContext authenticationContext

    @Autowired
    CorrelationContext correlationContext

    @Autowired
    LogService logService

    protected Optional<AuthenticationInfo> getAuthentication() {
        try {
            authenticationContext.authentication
        } catch (BeanCreationException exception) {
            logCaughtThrowable('No authentication is available', exception)
            Optional.empty()
        }
    }

    protected Optional<CorrelationInfo> getCorrelation() {
        try {
            correlationContext.correlation
        } catch (BeanCreationException exception) {
            logCaughtThrowable('No correlation is available', exception)
            Optional.empty()
        }
    }

    protected void logAsTrace(String message) {
        logAsTrace(message, null)
    }

    protected void logAsTrace(String message, Object payload) {
        log(LogLevel.TRACE, message, payload)
    }

    protected void logAsDebug(String message) {
        logAsDebug(message, null)
    }

    protected void logAsDebug(String message, Object payload) {
        log(LogLevel.DEBUG, message, payload)
    }

    protected void logAsInfo(String message) {
        logAsInfo(message, null)
    }

    protected void logAsInfo(String message, Object payload) {
        log(LogLevel.INFO, message, payload)
    }

    protected void logAsWarn(String message) {
        logAsWarn(message, null)
    }

    protected void logAsWarn(String message, Object payload) {
        log(LogLevel.WARN, message, payload)
    }

    protected void logCaughtThrowable(String message, Throwable throwable) {
        log(LogLevel.TRACE, message, null, throwable)
    }

    @PostConstruct
    protected void init() {

    }

    private void log(LogLevel level, String message, Object payload) {
        log(level, message, payload, null)
    }

    private void log(LogLevel level, String message, Object payload, Throwable throwable) {
        logService.log(new LogRecord(
                descriptor: this.class.name,
                level: level,
                message: message,
                payload: payload,
                throwable: throwable
        ))
    }
}
