package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import com.fasterxml.jackson.annotation.JsonIgnore

import ru.tchallenge.service.complex.reliability.violation.ViolationInfo

@CompileStatic
@Immutable
final class ViolationExceptionInfo implements ExceptionInfo {

    static ViolationExceptionInfo of(ViolationException exception) {
        new ViolationExceptionInfo(
                base: BaseExceptionInfo.of(ExceptionCategory.VIOLATION),
                violation: exception.violation
        )
    }

    static ViolationExceptionInfo of(ViolationInfo violation) {
        new ViolationExceptionInfo(
                base: BaseExceptionInfo.of(ExceptionCategory.VIOLATION),
                violation: violation
        )
    }

    @Delegate
    @JsonIgnore
    BaseExceptionInfo base

    ViolationInfo violation
}
