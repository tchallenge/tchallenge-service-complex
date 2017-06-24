package ru.tchallenge.service.complex.common.ordinal

import java.time.Instant
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

import groovy.transform.CompileStatic

@CompileStatic
@MappedSuperclass
abstract class GenericPrimaryEntity extends GenericOrdinalEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id

    @Column(name = "created_at")
    private Instant createdAt

    @Column(name = "last_modified_at")
    private Instant lastModifiedAt

    @Override
    Long getId() {
        return id
    }

    Instant getCreatedAt() {
        return createdAt
    }

    Instant getLastModifiedAt() {
        return lastModifiedAt
    }

    @Override
    protected void onInsert() {
        super.onInsert()
        createdAt = Instant.now()
        lastModifiedAt = createdAt
    }

    @Override
    protected void onUpdate() {
        super.onUpdate()
        lastModifiedAt = Instant.now()
    }
}
