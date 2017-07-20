package ru.tchallenge.service.complex.domain.person

import groovy.transform.CompileStatic

@CompileStatic
interface PersonMapper {

    Person asEntity(Person entity, PersonInvoice invoice)

    PersonInfo asInfo(Person entity)
}
