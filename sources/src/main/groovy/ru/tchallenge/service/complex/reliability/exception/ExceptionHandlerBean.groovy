package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic

import javax.servlet.http.HttpServletRequest

import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import ru.tchallenge.service.complex.common.GenericComponent

@CompileStatic
@ControllerAdvice
class ExceptionHandlerBean extends GenericComponent {

    @ExceptionHandler(HttpRequestMethodNotSupportedException)
    def handleNotSupportedMethodException(HttpServletRequest request, HttpRequestMethodNotSupportedException exception) {
        def info = info(request, exception)
        log(descriptor(exception), LogLevel.WARN, info.description, info, null)
        responseEntity(info, HttpStatus.METHOD_NOT_ALLOWED)
    }

    @ExceptionHandler(ViolationException)
    def handleViolationException(ViolationException exception) {
        def info = info(exception)
        log(descriptor(exception), LogLevel.INFO, info.violation.description, info, null)
        responseEntity(info, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(SecurityViolationException)
    def handleSecurityException(SecurityViolationException exception) {
        def info = info(exception)
        log(descriptor(exception), LogLevel.WARN, info.violation.description, info, null)
        responseEntity(info, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(NotSupportedException)
    def handleNotSupportedException(NotSupportedException exception) {
        def info = info(exception)
        log(descriptor(exception), LogLevel.WARN, info.description, info, null)
        responseEntity(info, HttpStatus.NOT_IMPLEMENTED)
    }

    @ExceptionHandler(UnsupportedOperationException)
    def handleUnsupportedException(UnsupportedOperationException exception) {
        def info = unsupportedOperationInfo()
        log(descriptor(exception), LogLevel.ERROR, info.description, info, exception)
        responseEntity(info, HttpStatus.NOT_IMPLEMENTED)
    }

    @ExceptionHandler(Throwable)
    def handleUnpredictedThrowable(Throwable throwable) {
        def info = info(ExceptionCategory.UNPREDICTED)
        log(descriptor(throwable), LogLevel.ERROR, info.description, info, throwable)
        responseEntity(info, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    private static String descriptor(Throwable throwable) {
        String result
        if (throwable instanceof OriginatedException) {
            result = (throwable as OriginatedException).origin.name
        } else {
            result = throwable.stackTrace[0].className
        }
        result
    }

    private static ResponseEntity<ExceptionInfo> responseEntity(ExceptionInfo info, HttpStatus status) {
        new ResponseEntity<>(info, status)
    }

    private static BaseExceptionInfo info(ExceptionCategory category) {
        new BaseExceptionInfo(
                id: uuid,
                category: category,
                description: category.description
        )
    }

    private static NotSupportedExceptionInfo info(NotSupportedException exception) {
        new NotSupportedExceptionInfo(
                base: info(ExceptionCategory.UNSUPPORTED),
                comment: exception.comment,
                deprecatedSince: exception.deprecatedSince,
                expectedSince: exception.expectedSince
        )
    }

    private static NotSupportedMethodExceptionInfo info(HttpServletRequest request, HttpRequestMethodNotSupportedException exception) {
        new NotSupportedMethodExceptionInfo(
                base: info(ExceptionCategory.UNSUPPORTED),
                method: exception.method,
                uri: request.requestURI
        )
    }

    private static NotSupportedExceptionInfo unsupportedOperationInfo() {
        new NotSupportedExceptionInfo(
                base: info(ExceptionCategory.UNSUPPORTED),
                comment: 'no information regarding deprecated or expected version is available',
        )
    }

    private static ViolationExceptionInfo info(ViolationException exception) {
        new ViolationExceptionInfo(
                base: info(ExceptionCategory.VIOLATION),
                violation: exception.violation
        )
    }
}
