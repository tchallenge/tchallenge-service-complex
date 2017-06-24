package ru.tchallenge.service.complex.behavior.component

import javax.annotation.PostConstruct

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.behavior.entity.GenericEntity

@CompileStatic
abstract class GenericBootstrap<E extends GenericEntity, ID extends Serializable> {

    @Autowired
    protected GenericRepository<E, ID> repository

    @PostConstruct
    protected void bootstrap() {
        entities().forEach { E entity -> repository.save(entity) }
    }

    protected abstract Collection<E> entities()
}
