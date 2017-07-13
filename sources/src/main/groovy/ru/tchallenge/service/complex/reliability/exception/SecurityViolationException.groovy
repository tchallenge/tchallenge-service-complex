package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.reliability.violation.ViolationInfo

@CompileStatic
class SecurityViolationException extends ViolationException {

    SecurityViolationException(ViolationInfo violation) {
        super(violation)
    }
}