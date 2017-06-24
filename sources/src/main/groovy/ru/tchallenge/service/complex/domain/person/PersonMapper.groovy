package ru.tchallenge.service.complex.domain.person

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericMapper
import ru.tchallenge.service.complex.convention.component.MapperComponent

@CompileStatic
@MapperComponent
class PersonMapper extends GenericMapper {

    Person asEntity(PersonInvoice invoice) {
        return new Person(
                firstname: invoice.firstname,
                lastname: invoice.lastname,
                quickname: invoice.quickname
        )
    }

    PersonInfo asInfo(Person entity) {
        return new PersonInfo(
                firstname: entity.firstname,
                lastname: entity.lastname,
                quickname: entity.quickname
        )
    }
}
