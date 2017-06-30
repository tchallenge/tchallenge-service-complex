package ru.tchallenge.service.complex.domain.employee

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformation.*
import ru.tchallenge.service.complex.common.GenericMapper
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
            roles = invoice.roles ? some(roleRepository, invoice.roles) : roles
            it
        }
    }

    EmployeeInfo asInfo(Employee entity) {
        return new EmployeeInfo(
                roles: infos(entity.roles)
        )
    }
}
