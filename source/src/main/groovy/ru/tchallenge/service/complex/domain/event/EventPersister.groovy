package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericPersister

@CompileStatic
interface EventPersister extends GenericPersister<Event> {

}
