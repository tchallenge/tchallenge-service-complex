package ru.tchallenge.service.complex.reliability.violation

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import com.fasterxml.jackson.annotation.JsonIgnore

@CompileStatic
@Immutable
final class SecurityViolationInfo implements ViolationInfo {

    static final ViolationCategory CATEGORY = ViolationCategory.SECURITY

    static final String TEXTCODE_PREFIX = 'X.SECURITY'

    static SecurityViolationInfo unauthenticated() {
        new SecurityViolationInfo(
                base: new BaseViolationInfo(
                        category: CATEGORY,
                        description: 'Unauthenticated access is restricted',
                        textcode: "${TEXTCODE_PREFIX}.UNAUTHENTICATED"
                )
        )
    }

    static SecurityViolationInfo unauthorized() {
        new SecurityViolationInfo(
                base: new BaseViolationInfo(
                        category: CATEGORY,
                        description: 'Request can not be fulfilled due to lack of authorization',
                        textcode: "${TEXTCODE_PREFIX}.UNAUTHORIZED"
                )
        )
    }

    @Delegate
    @JsonIgnore
    BaseViolationInfo base
}
