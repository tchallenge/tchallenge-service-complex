package ru.tchallenge.service.complex.domain.employee

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalEntity
import ru.tchallenge.service.complex.domain.account.Account
import ru.tchallenge.service.complex.domain.employee.role.EmployeeRole

@CompileStatic
@Entity
@Table(name = 'employee')
class Employee extends GenericOrdinalEntity {

    @NotNull
    @OneToOne
    @JoinColumn(name = 'account_id', nullable = false, unique = true, updatable = false)
    Account account

    @ManyToMany
    @JoinTable(name = 'employee_role_to_employee_map', joinColumns = @JoinColumn(name = 'employee_id'))
    Collection<EmployeeRole> roles = []
}
