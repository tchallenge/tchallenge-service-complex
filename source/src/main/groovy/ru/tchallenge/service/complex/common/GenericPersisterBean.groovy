package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

@CompileStatic
abstract class GenericPersisterBean<E extends GenericEntity, ID extends Serializable> extends GenericComponentBean {

    @Autowired
    protected GenericRepository<E, ID> repository

    E save(E entity) {
        prepare(entity)
        return repository.save(entity)
    }

    protected abstract void prepare(E entity)
}
