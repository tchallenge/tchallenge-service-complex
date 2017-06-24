package ru.tchallenge.service.complex.common.ordinal

import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.MappedSuperclass

import groovy.transform.CompileStatic

@CompileStatic
@MappedSuperclass
abstract class GenericSecondaryEntity extends GenericOrdinalEntity {

    @Id
    @Column(name = "id")
    Long id
}
