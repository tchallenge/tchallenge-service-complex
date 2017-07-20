package ru.tchallenge.service.complex.domain.employee.role

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Table

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = 'employee_role')
class EmployeeRole extends GenericEnumeratedEntity {

}
