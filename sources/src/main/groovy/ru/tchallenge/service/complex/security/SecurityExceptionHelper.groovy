package ru.tchallenge.service.complex.security

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.reliability.exception.SecurityViolationException
import ru.tchallenge.service.complex.reliability.violation.BaseViolationInfo
import ru.tchallenge.service.complex.reliability.violation.ViolationCategory

@CompileStatic
final class SecurityExceptionHelper {

    static SecurityViolationException unauthenticated() {
        return new SecurityViolationException(
                new BaseViolationInfo(
                        category: ViolationCategory.SECURITY,
                        description: "Unauthenticated access is restricted",
                        textcode: "X.SECURITY.UNAUTHENTICATED"
                )
        )
    }

    private SecurityExceptionHelper() {

    }
}
