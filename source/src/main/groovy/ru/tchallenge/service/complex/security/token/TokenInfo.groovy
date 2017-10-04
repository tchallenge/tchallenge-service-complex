package ru.tchallenge.service.complex.security.token

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import java.time.Duration
import java.time.Instant

import com.fasterxml.jackson.annotation.JsonIgnore

@CompileStatic
@Immutable(knownImmutableClasses = [Instant])
class TokenInfo {

    String id
    String payload
    Integer deactivationInMinutes
    Instant createdAt
    Instant expiresAt
    Instant lastUsedAt

    @JsonIgnore
    boolean isDeactivated() {
        Instant.now() > lastUsedAt + Duration.ofMinutes(deactivationInMinutes)
    }

    @JsonIgnore
    boolean isExpired() {
        Instant.now() > expiresAt
    }

    TokenInfo copyWithUpdatedLastUsage() {
        new TokenInfo(
                id: id,
                payload: payload,
                deactivationInMinutes: deactivationInMinutes,
                createdAt: createdAt,
                expiresAt: expiresAt,
                lastUsedAt: Instant.now()
        )
    }
}
