package ru.tchallenge.service.complex.domain.employee

import groovy.transform.CompileStatic

@CompileStatic
interface EmployeeMapper {

    Employee asEntity(Employee entity, EmployeeInvoice invoice)

    EmployeeInfo asInfo(Employee entity)
}
