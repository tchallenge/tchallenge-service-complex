package ru.tchallenge.service.complex.security.authentication

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import com.fasterxml.jackson.annotation.JsonIgnore

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.reliability.violation.BaseViolationInfo
import ru.tchallenge.service.complex.reliability.violation.ViolationInfo

@CompileStatic
@Immutable(knownImmutableClasses = [EnumeratedInfo])
final class AccountViolationInfo implements ViolationInfo {

    @Delegate
    @JsonIgnore
    BaseViolationInfo base

    String login
    EnumeratedInfo status
}
