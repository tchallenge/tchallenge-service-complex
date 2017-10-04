package ru.tchallenge.service.complex.security.token

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import com.fasterxml.jackson.annotation.JsonIgnore

import ru.tchallenge.service.complex.reliability.violation.BaseViolationInfo
import ru.tchallenge.service.complex.reliability.violation.SecurityViolationInfo
import ru.tchallenge.service.complex.reliability.violation.ViolationInfo

@CompileStatic
@Immutable
final class TokenViolationInfo implements ViolationInfo {

    static TokenViolationInfo deactivated(TokenInfo token) {
        def base = new SecurityViolationInfo(
                base: new BaseViolationInfo(
                        category: SecurityViolationInfo.CATEGORY,
                        description: 'Token has been deactivated due to absence of activity',
                        textcode: "${SecurityViolationInfo.TEXTCODE_PREFIX}.TOKEN.DEACTIVATED"
                )
        )
        new TokenViolationInfo(
                base: base,
                payload: token.payload,
                token: token
        )
    }

    static TokenViolationInfo expired(TokenInfo token) {
        def base = new SecurityViolationInfo(
                base: new BaseViolationInfo(
                        category: SecurityViolationInfo.CATEGORY,
                        description: 'Token has been expired',
                        textcode: "${SecurityViolationInfo.TEXTCODE_PREFIX}.TOKEN.EXPIRED"
                )
        )
        new TokenViolationInfo(
                base: base,
                payload: token.payload,
                token: token
        )
    }

    static TokenViolationInfo unknown(String payload) {
        def base = new SecurityViolationInfo(
                base: new BaseViolationInfo(
                        category: SecurityViolationInfo.CATEGORY,
                        description: 'Token payload has not been recognized',
                        textcode: "${SecurityViolationInfo.TEXTCODE_PREFIX}.TOKEN.UNKNOWN"
                )
        )
        new TokenViolationInfo(
                base: base,
                payload: payload
        )
    }

    @Delegate
    @JsonIgnore
    SecurityViolationInfo base

    String payload
    TokenInfo token
}
