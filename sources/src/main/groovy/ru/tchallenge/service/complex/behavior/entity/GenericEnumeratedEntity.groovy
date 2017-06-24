package ru.tchallenge.service.complex.behavior.entity

import javax.persistence.Column
import javax.persistence.MappedSuperclass

import groovy.transform.CompileStatic

@CompileStatic
@MappedSuperclass
abstract class GenericEnumeratedEntity extends GenericEntity<String> implements Comparable<GenericEnumeratedEntity> {

    @Column(name = "textcode")
    String textcode

    @Column(name = "title")
    String title

    @Column(name = "description")
    String description

    @Column(name = "stance")
    Integer stance

    @Override
    int compareTo(GenericEnumeratedEntity another) {
        return Integer.compare(stance, another.stance)
    }
}
