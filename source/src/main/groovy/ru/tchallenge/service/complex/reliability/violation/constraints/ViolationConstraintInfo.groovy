package ru.tchallenge.service.complex.reliability.violation.constraints

import groovy.transform.CompileStatic

@CompileStatic
interface ViolationConstraintInfo {

    String getDescription()

    String getTextcode()
}
