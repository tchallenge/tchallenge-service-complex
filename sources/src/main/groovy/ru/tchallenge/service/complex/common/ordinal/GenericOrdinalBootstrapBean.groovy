package ru.tchallenge.service.complex.common.ordinal

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericBootstrapBean

@CompileStatic
abstract class GenericOrdinalBootstrapBean<E extends GenericOrdinalEntity> extends GenericBootstrapBean<E, Long> {

    @Autowired
    protected GenericOrdinalPersisterBean<E> persister

    @Override
    protected void save(E entity) {
        persister.save(entity)
    }
}
