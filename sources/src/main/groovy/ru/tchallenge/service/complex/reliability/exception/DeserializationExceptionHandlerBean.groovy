package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic

import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import ru.tchallenge.service.complex.reliability.logging.LogRecord
import ru.tchallenge.service.complex.reliability.violation.DeserializationViolationInfo

@CompileStatic
@ControllerAdvice
class DeserializationExceptionHandlerBean extends BaseExceptionHandlerBean {

    @ExceptionHandler(HttpMessageNotReadableException)
    def handleNotReadableMessage(HttpMessageNotReadableException exception) {
        def violation = DeserializationViolationInfo.parse(exception)
        def info = ViolationExceptionInfo.of(violation)
        logService.log(new LogRecord(
                descriptor: descriptor(exception),
                level: LogLevel.INFO,
                message: info.violation.description,
                payload: info
        ))
        responseEntity(info, HttpStatus.BAD_REQUEST)
    }
}
