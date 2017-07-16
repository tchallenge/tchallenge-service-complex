package ru.tchallenge.service.complex.security.token

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import com.fasterxml.jackson.annotation.JsonIgnore

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations
import ru.tchallenge.service.complex.domain.account.Account
import ru.tchallenge.service.complex.reliability.violation.BaseViolationInfo
import ru.tchallenge.service.complex.reliability.violation.SecurityViolationInfo
import ru.tchallenge.service.complex.reliability.violation.ViolationInfo

@CompileStatic
@Immutable(knownImmutableClasses = [EnumeratedInfo])
final class AccountViolationInfo implements ViolationInfo {

    static AccountViolationInfo illegalCredentials(String login) {
        def base = new SecurityViolationInfo(
                base: new BaseViolationInfo(
                        category: SecurityViolationInfo.CATEGORY,
                        description: 'Specified credentials are illegal or not recognized',
                        textcode: "${SecurityViolationInfo.TEXTCODE_PREFIX}.ACCOUNT.CREDENTIALS.ILLEGAL"
                )
        )
        new AccountViolationInfo(
                base: base,
                login: login
        )
    }

    static AccountViolationInfo illegalStatus(Account account) {
        def base = new SecurityViolationInfo(
                base: new BaseViolationInfo(
                        category: SecurityViolationInfo.CATEGORY,
                        description: 'Account status is illegal for logon',
                        textcode: "${SecurityViolationInfo.TEXTCODE_PREFIX}.ACCOUNT.STATUS.ILLEGAL"
                )
        )
        new AccountViolationInfo(
                base: base,
                login: account.login,
                status: EnumeratedTransformations.info(account.status)
        )
    }

    static AccountViolationInfo unknown() {
        def base = new SecurityViolationInfo(
                base: new BaseViolationInfo(
                        category: SecurityViolationInfo.CATEGORY,
                        description: 'Referenced account does not exist',
                        textcode: "${SecurityViolationInfo.TEXTCODE_PREFIX}.ACCOUNT.UNKNOWN"
                )
        )
        new AccountViolationInfo(
                base: base
        )
    }

    @Delegate
    @JsonIgnore
    SecurityViolationInfo base

    String login
    EnumeratedInfo status
}
