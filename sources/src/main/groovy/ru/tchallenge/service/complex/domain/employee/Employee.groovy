package ru.tchallenge.service.complex.domain.employee

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToOne
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.ordinal.GenericSecondaryEntity
import ru.tchallenge.service.complex.domain.account.Account
import ru.tchallenge.service.complex.domain.employee.role.EmployeeRole

@CompileStatic
@Entity
@Table(name = "employee")
class Employee extends GenericSecondaryEntity {

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account

    @ManyToMany
    @JoinTable(name = "employee_role_to_employee_map", joinColumns = @JoinColumn(name = "employee_id"))
    Collection<EmployeeRole> roles
}
