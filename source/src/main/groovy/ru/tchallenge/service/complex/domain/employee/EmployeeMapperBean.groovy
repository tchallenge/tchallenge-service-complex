package ru.tchallenge.service.complex.domain.employee

import groovy.transform.PackageScope
import groovy.transform.TypeChecked

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent

@TypeChecked
@PackageScope
@MapperComponent
class EmployeeMapperBean extends GenericMapperBean implements EmployeeMapper {

    @Override
    EmployeeInfo asInfo(Employee entity) {
        new EmployeeInfo(
                roles: enumerateds.infos(entity.roles)
        )
    }
}
