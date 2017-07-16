package ru.tchallenge.service.complex.security.rescue

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import java.time.Instant

import com.fasterxml.jackson.annotation.JsonIgnore

import ru.tchallenge.service.complex.common.GenericInfoValue

@CompileStatic
@Immutable(knownImmutableClasses = [Instant])
class RescueInfo extends GenericInfoValue {

    String id
    String payload
    Instant createdAt
    Instant expiresAt

    @JsonIgnore
    boolean isExpired() {
        return now > expiresAt
    }
}
