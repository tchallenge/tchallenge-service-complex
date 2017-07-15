package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import ru.tchallenge.service.complex.common.GenericComponent
import ru.tchallenge.service.complex.reliability.correlation.CorrelationContext

@CompileStatic
@ControllerAdvice
class ExceptionHandlerBean extends GenericComponent {

    @Autowired
    CorrelationContext correlationContext

    @ExceptionHandler(ViolationException)
    def handleViolationException(ViolationException exception) {
        def info = info(exception)
        logAsInfo(info.violation.description, info)
        responseEntity(info, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(SecurityViolationException)
    def handleSecurityException(SecurityViolationException exception) {
        def info = info(exception)
        logAsWarn(info.violation.description, info)
        responseEntity(info, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(UnsupportedOperationException)
    def handleUnsupportedException(UnsupportedOperationException exception) {
        def info = info(ExceptionCategory.UNSUPPORTED)
        logAsError(info.description, exception)
        responseEntity(info, HttpStatus.NOT_IMPLEMENTED)
    }

    @ExceptionHandler(Throwable)
    def handleUnpredictedThrowable(Throwable throwable) {
        def info = info(ExceptionCategory.UNPREDICTED)
        logAsError(info.description, throwable)
        responseEntity(info, HttpStatus.INTERNAL_SERVER_ERROR)
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

    private static ViolationExceptionInfo info(ViolationException exception) {
        new ViolationExceptionInfo(
                base: info(ExceptionCategory.VIOLATION),
                violation: exception.violation
        )
    }
}
