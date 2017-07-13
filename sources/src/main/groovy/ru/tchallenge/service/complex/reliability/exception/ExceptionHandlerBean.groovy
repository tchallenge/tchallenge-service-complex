package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import ru.tchallenge.service.complex.common.GenericComponent
import ru.tchallenge.service.complex.reliability.correlation.CorrelationContext
import static ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals.uuid

@CompileStatic
@ControllerAdvice
class ExceptionHandlerBean extends GenericComponent {

    @Autowired
    CorrelationContext correlationContext

    @ExceptionHandler(ViolationException)
    ResponseEntity<?> handleViolationException(ViolationException exception) {
        def info = info(exception)
        logAsInfo(info)
        return responseEntity(info, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(SecurityViolationException)
    ResponseEntity<?> handleSecurityException(SecurityViolationException exception) {
        def info = info(exception)
        logAsWarn(info)
        return responseEntity(info, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(UnsupportedOperationException)
    ResponseEntity<?> handleUnsupportedException(UnsupportedOperationException exception) {
        def info = info(ExceptionCategory.UNSUPPORTED)
        logAsError(info, exception)
        return responseEntity(info, HttpStatus.NOT_IMPLEMENTED)
    }

    @ExceptionHandler(Throwable)
    ResponseEntity<?> handleUnpredictedThrowable(Throwable throwable) {
        def info = info(ExceptionCategory.UNPREDICTED)
        logAsError(info, throwable)
        return responseEntity(info, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    private BaseExceptionInfo info(ExceptionCategory category) {
        return new BaseExceptionInfo(
                id: uuid(),
                category: category,
                correlation: correlationContext.correlation,
                description: category.description
        )
    }

    private ViolationExceptionInfo info(ViolationException exception) {
        return new ViolationExceptionInfo(
                base: info(ExceptionCategory.VIOLATION),
                violation: exception.violation
        )
    }

    private static ResponseEntity<ExceptionInfo> responseEntity(ExceptionInfo info, HttpStatus status) {
        return new ResponseEntity<>(info, status)
    }
}
