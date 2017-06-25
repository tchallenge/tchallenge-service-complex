package ru.tchallenge.service.complex.common

import java.time.Instant
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

import groovy.transform.CompileStatic

@CompileStatic
@MappedSuperclass
abstract class GenericEntity<ID extends Serializable> implements Serializable {

    abstract ID getId()

    @PrePersist
    protected void onInsert() {

    }

    @PreUpdate
    protected void onUpdate() {

    }

    protected static Instant now() {
        return Instant.now()
    }

    protected static String uuid() {
        return UUID.randomUUID().toString()
    }
}
