package ru.tchallenge.service.complex.reliability.violation.constraints

import groovy.transform.CompileStatic
import groovy.transform.Immutable

@CompileStatic
@Immutable
class BaseViolationConstraintInfo implements ViolationConstraintInfo {

    static BaseViolationConstraintInfo required() {
        new BaseViolationConstraintInfo(
                description: 'Value is required and can not be null',
                textcode: 'REQUIRED'
        )
    }

    String description
    String textcode
}
