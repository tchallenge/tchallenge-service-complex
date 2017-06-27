package ru.tchallenge.service.complex.domain.maturity

import javax.persistence.Entity
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = "maturity")
class Maturity extends GenericEnumeratedEntity {

}
