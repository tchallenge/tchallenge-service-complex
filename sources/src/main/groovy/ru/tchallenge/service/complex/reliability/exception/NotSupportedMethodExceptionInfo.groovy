package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import com.fasterxml.jackson.annotation.JsonIgnore

@CompileStatic
@Immutable
final class NotSupportedMethodExceptionInfo implements ExceptionInfo {

    @Delegate
    @JsonIgnore
    BaseExceptionInfo base

    String method
    String uri
}
