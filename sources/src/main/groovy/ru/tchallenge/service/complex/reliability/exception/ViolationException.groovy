package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.reliability.violation.ViolationInfo

@CompileStatic
class ViolationException extends RuntimeException {

    private ViolationInfo violation

    ViolationException(ViolationInfo violation) {
        this.violation = violation
    }

    ViolationInfo getViolation() {
        return violation
    }
}
