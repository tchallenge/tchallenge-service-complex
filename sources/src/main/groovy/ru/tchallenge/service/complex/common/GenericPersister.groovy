package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

@CompileStatic
interface GenericPersister<E extends GenericEntity> {

    E save(E entity)
}
