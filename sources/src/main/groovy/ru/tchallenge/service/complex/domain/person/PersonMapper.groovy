package ru.tchallenge.service.complex.domain.person

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericMapper
import ru.tchallenge.service.complex.convention.component.MapperComponent

@CompileStatic
@MapperComponent
class PersonMapper extends GenericMapper {

    PersonInfo asInfo(Person entity) {
        return new PersonInfo(
                firstname: entity.firstname,
                lastname: entity.lastname,
                quickname: entity.quickname
        )
    }

    Person merge(Person entity, PersonInvoice invoice) {
        entity = entity ?: new Person()
        return entity.with {
            id = invoice.id ? invoice.id as Long : id
            it
        }
    }
}
