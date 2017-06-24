package ru.tchallenge.service.complex.common.complementary

import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.MappedSuperclass

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericEntity

@CompileStatic
@MappedSuperclass
abstract class GenericComplementaryEntity extends GenericEntity<String> {

    @Id
    @Column(name = "id")
    String id
}
