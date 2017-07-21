package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

import java.time.Instant
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

import ru.tchallenge.service.complex.common.timestamp.TimestampedEntity
import ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals

@CompileStatic
@MappedSuperclass
abstract class GenericEntity<ID extends Serializable> implements Serializable {

    protected static Instant getNow() {
        Foundamentals.now
    }

    protected static String getUuid() {
        Foundamentals.uuid
    }

    @PrePersist
    protected void onInsert() {
        if (this instanceof TimestampedEntity) {
            def $timestamped = this as TimestampedEntity
            $timestamped.initCreatedAt()
            $timestamped.updateLastModifiedAt()
        }
    }

    @PreUpdate
    protected void onUpdate() {
        if (this instanceof TimestampedEntity) {
            def $timestamped = this as TimestampedEntity
            $timestamped.updateLastModifiedAt()
        }
    }
}
