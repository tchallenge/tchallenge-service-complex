package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.reliability.violation.ViolationInfo

@CompileStatic
class ResourceViolationException extends ViolationException {

    ResourceViolationException(Class<?> origin, ViolationInfo violation) {
        super(origin, violation)
    }
}
