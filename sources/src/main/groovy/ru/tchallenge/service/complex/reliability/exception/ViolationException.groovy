package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.reliability.violation.ViolationInfo

@CompileStatic
class ViolationException extends OriginatedException {

    private final ViolationInfo violation

    ViolationException(Class<?> origin, ViolationInfo violation) {
        super(origin)
        this.violation = violation
    }

    ViolationInfo getViolation() {
        violation
    }
}
