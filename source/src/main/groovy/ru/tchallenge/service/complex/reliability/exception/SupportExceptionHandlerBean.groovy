package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import javax.servlet.http.HttpServletRequest

import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import ru.tchallenge.service.complex.reliability.logging.LogRecord

@CompileStatic
@PackageScope
@ControllerAdvice
class SupportExceptionHandlerBean extends BaseExceptionHandlerBean {

    @ExceptionHandler(HttpRequestMethodNotSupportedException)
    def handleNotSupportedMethod(HttpServletRequest request, HttpRequestMethodNotSupportedException exception) {
        def info = NotSupportedMethodExceptionInfo.of(request, exception)
        logService.log(new LogRecord(
                descriptor: descriptor(exception),
                level: LogLevel.WARN,
                message: info.description,
                payload: info
        ))
        responseEntity(info, HttpStatus.METHOD_NOT_ALLOWED)
    }

    @ExceptionHandler(NotSupportedException)
    def handleNotSupported(NotSupportedException exception) {
        def info = NotSupportedExceptionInfo.of(exception)
        logService.log(new LogRecord(
                descriptor: descriptor(exception),
                level: LogLevel.WARN,
                message: info.description,
                payload: info
        ))
        responseEntity(info, HttpStatus.NOT_IMPLEMENTED)
    }

    @ExceptionHandler(UnsupportedOperationException)
    def handleUnsupported(UnsupportedOperationException exception) {
        def info = NotSupportedExceptionInfo.ofUnsupportedOperation()
        logService.log(new LogRecord(
                descriptor: descriptor(exception),
                level: LogLevel.ERROR,
                message: info.description,
                payload: info,
                throwable: exception
        ))
        responseEntity(info, HttpStatus.NOT_IMPLEMENTED)
    }
}
