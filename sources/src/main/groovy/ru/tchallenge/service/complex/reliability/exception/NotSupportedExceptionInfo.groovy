package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import com.fasterxml.jackson.annotation.JsonIgnore

@CompileStatic
@Immutable
final class NotSupportedExceptionInfo implements ExceptionInfo {

    static NotSupportedExceptionInfo of(NotSupportedException exception) {
        new NotSupportedExceptionInfo(
                base: BaseExceptionInfo.of(ExceptionCategory.UNSUPPORTED),
                comment: exception.comment,
                deprecatedSince: exception.deprecatedSince,
                expectedSince: exception.expectedSince
        )
    }

    static NotSupportedExceptionInfo ofUnsupportedOperation() {
        new NotSupportedExceptionInfo(
                base: BaseExceptionInfo.of(ExceptionCategory.UNSUPPORTED),
                comment: 'no information regarding deprecated or expected version is available',
        )
    }

    @Delegate
    @JsonIgnore
    BaseExceptionInfo base

    String comment
    String deprecatedSince
    String expectedSince
}
