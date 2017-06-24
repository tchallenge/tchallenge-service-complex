package ru.tchallenge.service.complex.domain.employee.role

import javax.persistence.Entity
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = "employee_role")
class EmployeeRole extends GenericEnumeratedEntity {

}
