package ru.tchallenge.service.complex.common

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
}
