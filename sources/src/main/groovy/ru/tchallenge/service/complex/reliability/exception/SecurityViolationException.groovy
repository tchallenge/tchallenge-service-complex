package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.reliability.violation.SecurityViolationInfo
import ru.tchallenge.service.complex.reliability.violation.ViolationInfo

@CompileStatic
class SecurityViolationException extends ViolationException {

    static SecurityViolationException unauthenticated(Object origin) {
        of(origin, SecurityViolationInfo.unauthenticated())
    }

    static SecurityViolationException unauthorized(Object origin) {
        of(origin, SecurityViolationInfo.unauthorized())
    }

    static SecurityViolationException of(Object origin, ViolationInfo violation) {
        new SecurityViolationException(origin.class, violation)
    }

    SecurityViolationException(Class<?> origin, ViolationInfo violation) {
        super(origin, violation)
    }
}
