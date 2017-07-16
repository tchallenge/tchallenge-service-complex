package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import ru.tchallenge.service.complex.common.GenericComponent

@CompileStatic
abstract class BaseExceptionHandlerBean extends GenericComponent {

    protected static String descriptor(Throwable throwable) {
        String result
        if (throwable instanceof OriginatedException) {
            result = (throwable as OriginatedException).origin.name
        } else {
            result = throwable.stackTrace[0].className
        }
        result
    }

    protected static ResponseEntity<ExceptionInfo> responseEntity(ExceptionInfo info, HttpStatus status) {
        new ResponseEntity<>(info, status)
    }
}
