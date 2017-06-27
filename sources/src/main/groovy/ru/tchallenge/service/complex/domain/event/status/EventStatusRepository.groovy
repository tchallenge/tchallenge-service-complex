package ru.tchallenge.service.complex.domain.event.status

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository

@CompileStatic
interface EventStatusRepository extends GenericEnumeratedRepository<EventStatus> {

}
