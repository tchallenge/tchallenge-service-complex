package ru.tchallenge.service.complex.common

import java.time.Instant
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.timestamp.TimestampedEntity

@CompileStatic
@MappedSuperclass
abstract class GenericEntity<ID extends Serializable> implements Serializable {

    @PrePersist
    protected void onInsert() {
        if (this instanceof TimestampedEntity) {
            def timestamped = this as TimestampedEntity
            timestamped.createdAt = now()
            timestamped.lastModifiedAt = timestamped.createdAt
        }
    }

    @PreUpdate
    protected void onUpdate() {
        if (this instanceof TimestampedEntity) {
            def timestamped = this as TimestampedEntity
            timestamped.lastModifiedAt = now()
        }
    }

    protected static Instant now() {
        return Instant.now()
    }

    protected static String uuid() {
        return UUID.randomUUID().toString()
    }
}
