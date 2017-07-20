package ru.tchallenge.service.complex.domain.person

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent

@CompileStatic
@PackageScope
@MapperComponent
class PersonMapperBean extends GenericMapperBean implements PersonMapper {

    @Override
    Person asEntity(Person entity, PersonInvoice invoice) {
        def $result = entity ?: new Person()
        $result.with {
            id = invoice.id as Long ?: id
            firstname = invoice.firstname ?: firstname
            lastname = invoice.lastname ?: lastname
            quickname = invoice.quickname ?: quickname
            it
        }
    }

    @Override
    PersonInfo asInfo(Person entity) {
        new PersonInfo(
                firstname: entity.firstname,
                lastname: entity.lastname,
                quickname: entity.quickname
        )
    }
}
