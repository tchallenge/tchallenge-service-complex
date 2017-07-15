package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

import java.time.Instant
import java.util.function.Function
import javax.annotation.PostConstruct

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.logging.LogLevel

import ru.tchallenge.service.complex.reliability.logging.LogRecord
import ru.tchallenge.service.complex.reliability.logging.LogService
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
    LogService logService

    void log(LogLevel level, String message, Object payload) {
        log(level, message, payload, null)
    }

    void log(LogLevel level, String message, Object payload, Throwable throwable) {
        def descriptor = throwable ? throwable.stackTrace[0].className : this.class.name
        log(descriptor, level, message, payload, throwable)
    }

    void log(String descriptor, LogLevel level, String message, Object payload, Throwable throwable) {
        def record = new LogRecord(
                descriptor: descriptor,
                level: level,
                message: message,
                payload: payload,
                throwable: throwable
        )
        logService.log(record)
    }

    void logAsTrace(String message) {
        logAsTrace(message, null)
    }

    void logAsTrace(String message, Object payload) {
        log(LogLevel.TRACE, message, payload)
    }

    void logAsTraceCaughtThrowable(Throwable throwable) {
        log(this.class.name, LogLevel.TRACE, throwable.message, null, throwable)
    }

    void logAsDebug(String message) {
        logAsDebug(message, null)
    }

    void logAsDebug(String message, Object payload) {
        log(LogLevel.DEBUG, message, payload)
    }

    void logAsInfo(String message) {
        logAsInfo(message, null)
    }

    void logAsInfo(String message, Object payload) {
        log(LogLevel.INFO, message, payload)
    }

    void logAsWarn(String message) {
        logAsWarn(message, null)
    }

    void logAsWarn(String message, Object payload) {
        log(LogLevel.WARN, message, payload)
    }

    void logAsError(String message, Throwable throwable) {
        logAsError(message, null, throwable)
    }

    void logAsError(String message, Object payload, Throwable throwable) {
        log(LogLevel.ERROR, message, payload, throwable)
    }

    void logAsFatal(String message, Throwable throwable) {
        logAsFatal(message, null, throwable)
    }

    void logAsFatal(String message, Object payload, Throwable throwable) {
        log(LogLevel.FATAL, message, payload, throwable)
    }

    @PostConstruct
    protected void init() {

    }
}
