package ru.tchallenge.service.complex.security.token

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import ru.tchallenge.service.complex.reliability.violation.BaseViolationInfo
import ru.tchallenge.service.complex.reliability.violation.ViolationInfo

@CompileStatic
@Immutable
final class TokenViolationInfo implements ViolationInfo {

    @Delegate
    BaseViolationInfo base

    String payload
    TokenInfo token
}
