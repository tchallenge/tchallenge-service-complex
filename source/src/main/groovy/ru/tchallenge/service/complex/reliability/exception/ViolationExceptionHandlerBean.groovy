package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.boot.logging.LogLevel
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import ru.tchallenge.service.complex.reliability.logging.LogRecord

@CompileStatic
@PackageScope
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ViolationExceptionHandlerBean extends BaseExceptionHandlerBean {

    @ExceptionHandler(SecurityViolationException)
    def handleSecurityViolation(SecurityViolationException exception) {
        def info = ViolationExceptionInfo.of(exception)
        logService.log(new LogRecord(
                descriptor: descriptor(exception),
                level: LogLevel.WARN,
                message: info.violation.description,
                payload: info
        ))
        responseEntity(info, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(ViolationException)
    def handleViolation(ViolationException exception) {
        def info = ViolationExceptionInfo.of(exception)
        logService.log(new LogRecord(
                descriptor: descriptor(exception),
                level: LogLevel.INFO,
                message: info.violation.description,
                payload: info
        ))
        responseEntity(info, HttpStatus.BAD_REQUEST)
    }
}
