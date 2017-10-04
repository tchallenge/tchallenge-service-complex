package ru.tchallenge.service.complex.common.ordinal.sequence

import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

import ru.tchallenge.service.complex.common.GenericEntity

@CompileStatic
@Entity
@Table(name = 'ordinal_sequence')
class OrdinalSequence extends GenericEntity<String> {

    @NotNull
    @Id
    @Column(name = 'id', nullable = false, unique = true, updatable = false)
    String id

    @NotNull
    @Column(name = 'description', nullable = false, updatable = false)
    String description

    @Min(1L)
    @Column(name = 'current_value')
    Long currentValue

    @NotNull
    @Min(1L)
    @Column(name = 'initial_value', nullable = false, updatable = false)
    Long initialValue

    @NotNull
    @Min(1L)
    @Column(name = 'step', nullable = false, updatable = false)
    Integer step
}
