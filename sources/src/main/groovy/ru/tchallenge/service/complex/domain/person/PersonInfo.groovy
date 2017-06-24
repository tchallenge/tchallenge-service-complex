package ru.tchallenge.service.complex.domain.person

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInfoValue

@CompileStatic
class PersonInfo extends GenericInfoValue {

    String firstname
    String lastname
    String quickname
}
