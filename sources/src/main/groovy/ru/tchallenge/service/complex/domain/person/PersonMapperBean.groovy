package ru.tchallenge.service.complex.domain.person

import groovy.transform.PackageScope
import groovy.transform.TypeChecked

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent

@TypeChecked
@PackageScope
@MapperComponent
class PersonMapperBean extends GenericMapperBean implements PersonMapper {

    @Override
    PersonInfo asInfo(Person entity) {
        new PersonInfo(
                firstname: entity.firstname,
                lastname: entity.lastname,
                quickname: entity.quickname
        )
    }
}
