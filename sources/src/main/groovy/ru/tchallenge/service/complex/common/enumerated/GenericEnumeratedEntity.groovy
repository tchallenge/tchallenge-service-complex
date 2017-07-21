package ru.tchallenge.service.complex.common.enumerated

import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

import ru.tchallenge.service.complex.common.GenericEntity

@CompileStatic
@MappedSuperclass
abstract class GenericEnumeratedEntity extends GenericEntity<String> implements Comparable<GenericEnumeratedEntity> {

    @Id
    @Column(name = 'id', nullable = false, unique = true, updatable = false)
    String id

    @NotNull
    @Column(name = 'textcode', nullable = false, unique = true, updatable = false)
    String textcode

    @NotNull
    @Column(name = 'title', nullable = false, updatable = false)
    String title

    @Column(name = 'description', updatable = false)
    String description

    @NotNull
    @Min(1)
    @Column(name = 'stance', nullable = false, unique = true, updatable = false)
    Integer stance

    @Override
    int compareTo(GenericEnumeratedEntity another) {
        Integer.compare(stance, another.stance)
    }
}
