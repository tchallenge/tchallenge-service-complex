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

    Employee asEntity(Employee entity, EmployeeInvoice invoice) {
        entity = entity ?: new Employee()
        return entity.with {
            id = invoice.id as Long ?: id
            roles = invoice.roles ? EnumeratedHelper.many(roleRepository, invoice.roles) : roles
            it
        }
    }

    EmployeeInfo asInfo(Employee entity) {
        return new EmployeeInfo(
                roles: EnumeratedHelper.many(entity.roles)
        )
    }
}
