package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

@CompileStatic
abstract class GenericBootstrap<E extends GenericEntity, ID extends Serializable>
        extends GenericComponent
        implements Bootstrap<E, ID> {

    @Autowired
    protected GenericRepository<E, ID> repository

    @Override
    void run() {
        entities().forEach { E entity -> save(entity) }
    }

    protected abstract Collection<E> entities()

    protected void save(E entity) {
        repository.save(entity)
    }
}
