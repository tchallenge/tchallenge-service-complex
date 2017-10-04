package ru.tchallenge.service.complex.security.voucher

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import java.time.Instant

import com.fasterxml.jackson.annotation.JsonIgnore

import ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals

@CompileStatic
@Immutable(knownImmutableClasses = [Instant])
class VoucherInfo {

    String id
    String payload
    Instant createdAt
    Instant expiresAt

    @JsonIgnore
    boolean isExpired() {
        return Foundamentals.now > expiresAt
    }
}
