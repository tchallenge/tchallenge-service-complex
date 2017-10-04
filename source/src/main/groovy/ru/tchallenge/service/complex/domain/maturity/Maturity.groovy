package ru.tchallenge.service.complex.domain.maturity

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Table

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = 'maturity')
class Maturity extends GenericEnumeratedEntity {

}
