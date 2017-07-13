package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import com.fasterxml.jackson.annotation.JsonIgnore

import ru.tchallenge.service.complex.reliability.violation.ViolationInfo

@CompileStatic
@Immutable
final class ViolationExceptionInfo implements ExceptionInfo {

    @Delegate
    @JsonIgnore
    BaseExceptionInfo base

    ViolationInfo violation
}
