package ru.tchallenge.service.complex.common

import java.time.Instant
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.timestamp.TimestampedEntity
import ru.tchallenge.service.complex.utility.miscellaneous.Essentials

@CompileStatic
@MappedSuperclass
abstract class GenericEntity<ID extends Serializable> implements Essentials, Serializable {

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
}
