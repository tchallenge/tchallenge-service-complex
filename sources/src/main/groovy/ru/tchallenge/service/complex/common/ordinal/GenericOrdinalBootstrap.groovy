package ru.tchallenge.service.complex.common.ordinal

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericBootstrap

@CompileStatic
abstract class GenericOrdinalBootstrap<E extends GenericOrdinalEntity> extends GenericBootstrap<E, Long> {

    @Autowired
    protected GenericOrdinalPersister<E> persister

    @Override
    protected void save(E entity) {
        persister.save(entity)
    }
}
