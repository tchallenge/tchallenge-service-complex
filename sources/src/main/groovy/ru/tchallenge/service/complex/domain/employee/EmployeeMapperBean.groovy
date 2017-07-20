package ru.tchallenge.service.complex.domain.employee

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent
import ru.tchallenge.service.complex.domain.employee.role.EmployeeRoleRepository

@CompileStatic
@PackageScope
@MapperComponent
class EmployeeMapperBean extends GenericMapperBean implements EmployeeMapper {

    @Autowired
    EmployeeRoleRepository roleRepository

    @Override
    Employee asEntity(Employee entity, EmployeeInvoice invoice) {
        def $result = entity ?: new Employee()
        $result.with {
            id = invoice.id as Long ?: id
            roles = invoice.roles ? enumerateds.some(roleRepository, invoice.roles) : roles
            it
        }
    }

    @Override
    EmployeeInfo asInfo(Employee entity) {
        new EmployeeInfo(
                roles: enumerateds.infos(entity.roles)
        )
    }
}
