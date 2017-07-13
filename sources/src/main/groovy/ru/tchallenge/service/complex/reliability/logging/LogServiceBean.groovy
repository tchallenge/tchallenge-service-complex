package ru.tchallenge.service.complex.reliability.logging

import groovy.transform.CompileStatic

import org.springframework.boot.logging.LogLevel

import org.apache.commons.logging.LogFactory

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@ServiceComponent
class LogServiceBean extends GenericService implements LogService {

    @Override
    void log(LogRecord record) {
        def log = LogFactory.getLog(record.descriptor)
        def level = record.level
        def message = record.message
        def throwable = record.throwable
        if (level == LogLevel.TRACE && log.traceEnabled) {
            log.trace(message, throwable)
        } else if (level == LogLevel.DEBUG && log.debugEnabled) {
            log.debug(message, throwable)
        } else if (level == LogLevel.INFO && log.infoEnabled) {
            log.info(message, throwable)
        } else if (level == LogLevel.WARN && log.warnEnabled) {
            log.warn(message, throwable)
        } else if (level == LogLevel.ERROR && log.errorEnabled) {
            log.error(message, throwable)
        } else if (level == LogLevel.FATAL && log.fatalEnabled) {
            log.fatal(message, throwable)
        }
    }
}
