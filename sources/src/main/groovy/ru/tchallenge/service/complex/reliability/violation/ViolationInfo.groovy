package ru.tchallenge.service.complex.reliability.violation

import groovy.transform.CompileStatic

@CompileStatic
interface ViolationInfo {

    ViolationCategory getCategory()

    String getDescription()

    String getTextcode()
}
