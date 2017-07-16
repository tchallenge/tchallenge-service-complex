package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic

import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import ru.tchallenge.service.complex.reliability.logging.LogRecord

@CompileStatic
@ControllerAdvice
class SecurityExceptionHandlerBean extends BaseExceptionHandlerBean {

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
}
