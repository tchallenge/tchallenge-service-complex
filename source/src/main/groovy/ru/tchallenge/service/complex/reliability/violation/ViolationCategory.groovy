package ru.tchallenge.service.complex.reliability.violation

import groovy.transform.CompileStatic

@CompileStatic
enum ViolationCategory {

    CONTRACT,

    RESOURCE,

    SECURITY,

    STATE
}
