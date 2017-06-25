package ru.tchallenge.service.complex.security.token

import java.time.Duration
import java.time.Instant

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import ru.tchallenge.service.complex.common.GenericInfoValue

@CompileStatic
@Immutable(knownImmutableClasses = [Instant])
class TokenInfo extends GenericInfoValue {

    String id
    String payload
    Integer deactivationInMinutes
    Instant createdAt
    Instant expiresAt
    Instant lastUsedAt

    boolean isDeactivated() {
        return now() > lastUsedAt + Duration.ofMinutes(deactivationInMinutes)
    }

    boolean isExpired() {
        return now() > expiresAt
    }

    TokenInfo copyWithUpdatedLastUsage() {
        return new TokenInfo(
                id: id,
                payload: payload,
                deactivationInMinutes: deactivationInMinutes,
                createdAt: createdAt,
                expiresAt: expiresAt,
                lastUsedAt: now()
        )
    }
}
