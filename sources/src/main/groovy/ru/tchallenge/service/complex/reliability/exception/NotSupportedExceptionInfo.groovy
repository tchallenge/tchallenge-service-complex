package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import com.fasterxml.jackson.annotation.JsonIgnore

@CompileStatic
@Immutable
final class NotSupportedExceptionInfo implements ExceptionInfo {

    @Delegate
    @JsonIgnore
    BaseExceptionInfo base

    String comment
    String deprecatedSince
    String expectedSince
}
