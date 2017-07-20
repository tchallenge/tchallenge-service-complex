package ru.tchallenge.service.complex.domain.person

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent

@CompileStatic
@MapperComponent
class PersonMapper extends GenericMapperBean {

    Person asEntity(Person entity, PersonInvoice invoice) {
        entity = entity ?: new Person()
        return entity.with {
            id = invoice.id as Long ?: id
            firstname = invoice.firstname ?: firstname
            lastname = invoice.lastname ?: lastname
            quickname = invoice.quickname ?: quickname
            it
        }
    }

    PersonInfo asInfo(Person entity) {
        return new PersonInfo(
                firstname: entity.firstname,
                lastname: entity.lastname,
                quickname: entity.quickname
        )
    }
}
