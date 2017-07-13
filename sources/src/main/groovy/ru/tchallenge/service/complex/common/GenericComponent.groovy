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

    void log(LogLevel level, Object message, Throwable throwable) {
        logService.log(new LogRecord(
                descriptor: this.class.name,
                level: level,
                message: message,
                throwable: throwable
        ))
    }

    void logAsTrace(Object message) {
        log(LogLevel.TRACE, message, null)
    }

    void logAsDebug(Object message) {
        log(LogLevel.DEBUG, message, null)
    }

    void logAsInfo(Object message) {
        log(LogLevel.INFO, message, null)
    }

    void logAsWarn(Object message) {
        log(LogLevel.WARN, message, null)
    }

    void logAsError(Object message, Throwable throwable) {
        log(LogLevel.ERROR, message, throwable)
    }

    @PostConstruct
    protected void init() {

    }
}
