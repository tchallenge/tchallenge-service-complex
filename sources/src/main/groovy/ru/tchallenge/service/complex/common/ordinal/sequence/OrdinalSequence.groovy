package ru.tchallenge.service.complex.common.ordinal.sequence

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericEntity

@CompileStatic
@Entity
@Table(name = "ordinal_sequence")
class OrdinalSequence extends GenericEntity<String> {

    @Id
    @Column(name = "id")
    String id

    @Column(name = "description")
    String description

    @Column(name = "current_value")
    Long currentValue

    @Column(name = "initial_value")
    Long initialValue

    @Column(name = "step")
    Integer step
}
