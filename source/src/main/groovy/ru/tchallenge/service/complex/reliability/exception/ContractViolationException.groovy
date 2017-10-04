package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.reliability.violation.ViolationInfo

@CompileStatic
class ContractViolationException extends ViolationException {

    ContractViolationException(Class<?> origin, ViolationInfo violation) {
        super(origin, violation)
    }
}
