package ru.tchallenge.service.complex.domain.employee

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericMapper
import ru.tchallenge.service.complex.common.enumerated.EnumeratedHelper
import ru.tchallenge.service.complex.convention.component.MapperComponent
import ru.tchallenge.service.complex.domain.employee.role.EmployeeRoleRepository

@CompileStatic
@MapperComponent
class EmployeeMapper extends GenericMapper {

    @Autowired
    protected EmployeeRoleRepository roleRepository

    Employee asEntity(EmployeeInvoice invoice) {
        return new Employee(
                roles: EnumeratedHelper.many(invoice.roles, roleRepository)
        )
    }

    EmployeeInfo asInfo(Employee entity) {
        return new EmployeeInfo(
                roles: EnumeratedHelper.many(entity.roles)
        )
    }
}
