package ru.tchallenge.service.complex.behavior.entity

import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

import groovy.transform.CompileStatic

@CompileStatic
@MappedSuperclass
abstract class GenericEntity<ID extends Serializable> implements Serializable {

    @Id
    @Column(name = "id")
    ID id

    @PrePersist
    protected void onInsert() {

    }

    @PreUpdate
    protected void onUpdate() {

    }
}
