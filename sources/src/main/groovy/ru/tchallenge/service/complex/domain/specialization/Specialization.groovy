package ru.tchallenge.service.complex.domain.specialization

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Table

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = 'specialization')
class Specialization extends GenericEnumeratedEntity {

    // TODO: this entity must NOT be Enumerated
}
