package ru.tchallenge.service.complex.domain.candidate

import javax.persistence.Entity
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.ordinal.GenericSecondaryEntity

@CompileStatic
@Entity
@Table(name = "candidate")
class Candidate extends GenericSecondaryEntity {

}
