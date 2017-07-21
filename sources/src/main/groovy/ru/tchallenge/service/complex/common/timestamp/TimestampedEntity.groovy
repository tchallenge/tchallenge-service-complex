package ru.tchallenge.service.complex.common.timestamp

import groovy.transform.CompileStatic

import java.time.Instant
import javax.persistence.Column

import ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals

@CompileStatic
trait TimestampedEntity {

    @Column(name = 'created_at', nullable = false, updatable = false)
    private Instant createdAt

    @Column(name = 'last_modified_at', nullable = false)
    private Instant lastModifiedAt

    Instant getCreatedAt() {
        createdAt
    }

    Instant getLastModifiedAt() {
        lastModifiedAt
    }

    void initCreatedAt() {
        createdAt = Foundamentals.now
    }

    void updateLastModifiedAt() {
        lastModifiedAt = Foundamentals.now
    }
}
