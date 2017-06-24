package ru.tchallenge.service.complex.domain.employee

import javax.persistence.Entity
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.ordinal.GenericSecondaryEntity

@CompileStatic
@Entity
@Table(name = "employee")
class Employee extends GenericSecondaryEntity {

}
