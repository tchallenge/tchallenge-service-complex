package ru.tchallenge.service.complex.domain.person

import groovy.transform.TypeChecked

@TypeChecked
interface PersonMapper {

    PersonInfo asInfo(Person entity)
}
