package ru.tchallenge.service.complex.common

import javax.annotation.PostConstruct

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.logging.LogLevel

import ru.tchallenge.service.complex.reliability.logging.LogRecord
import ru.tchallenge.service.complex.reliability.logging.LogService

@CompileStatic
abstract class GenericComponent {

    @Autowired
    LogService logService

    void log(LogLevel level, String message, Object payload) {
        log(level, message, payload, null)
    }

    void log(LogLevel level, String message, Object payload, Throwable throwable) {
        logService.log(new LogRecord(
                descriptor: throwable ? throwable.stackTrace[0].className : this.class.name,
                level: level,
                message: message,
                payload: payload,
                throwable: throwable
        ))
    }

    void logAsTrace(String message, Object payload) {
        log(LogLevel.TRACE, message, payload)
    }

    void logAsDebug(String message, Object payload) {
        log(LogLevel.DEBUG, message, payload)
    }

    void logAsInfo(String message, Object payload) {
        log(LogLevel.INFO, message, payload)
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
