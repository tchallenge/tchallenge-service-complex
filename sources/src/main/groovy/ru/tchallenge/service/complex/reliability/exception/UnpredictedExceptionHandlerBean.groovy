package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic

import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import ru.tchallenge.service.complex.reliability.logging.LogRecord

@CompileStatic
@ControllerAdvice
class UnpredictedExceptionHandlerBean extends BaseExceptionHandlerBean {

    @ExceptionHandler(Throwable)
    def handleUnpredicted(Throwable throwable) {
        def info = BaseExceptionInfo.of(ExceptionCategory.UNPREDICTED)
        logService.log(new LogRecord(
                descriptor: descriptor(throwable),
                level: LogLevel.ERROR,
                message: info.description,
                payload: info,
                throwable: throwable
        ))
        responseEntity(info, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
