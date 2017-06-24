package ru.tchallenge.service.complex.common.enumerated

import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.MappedSuperclass

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericEntity

@CompileStatic
@MappedSuperclass
abstract class GenericEnumeratedEntity extends GenericEntity<String> implements Comparable<GenericEnumeratedEntity> {

    @Id
    @Column(name = "id")
    String id

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
