package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

@CompileStatic
abstract class GenericPersister<E extends GenericEntity, ID extends Serializable> extends GenericComponent {

    @Autowired
    protected GenericRepository<E, ID> repository

    E save(E entity) {
        prepare(entity)
        return repository.save(entity)
    }

    protected abstract void prepare(E entity)
}
