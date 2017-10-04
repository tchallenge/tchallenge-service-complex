package ru.tchallenge.service.complex.reliability.violation

import groovy.transform.CompileStatic
import groovy.transform.Immutable

@CompileStatic
@Immutable
final class BaseViolationInfo implements ViolationInfo {

    ViolationCategory category
    String description
    String textcode
}
