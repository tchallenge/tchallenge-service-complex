package ru.tchallenge.service.complex.behavior.entity

import javax.persistence.Column
import javax.persistence.MappedSuperclass

import groovy.transform.CompileStatic

@CompileStatic
@MappedSuperclass
abstract class GenericEnumeratedEntity extends GenericEntity<String> {

    @Column(name = "textcode")
    String textcode

    @Column(name = "title")
    String title

    @Column(name = "description")
    String description

    @Column(name = "stance")
    Integer stance
}
