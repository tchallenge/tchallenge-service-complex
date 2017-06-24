package ru.tchallenge.service.complex.behavior.component

import javax.annotation.PostConstruct

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.behavior.entity.GenericEntity

abstract class GenericBootstrap<E extends GenericEntity, ID extends Serializable> {

    @Autowired
    protected GenericRepository<E, ID> repository

    @PostConstruct
    protected void bootstrap() {
        entities().forEach { repository.save(it) }
    }

    protected abstract Collection<E> entities()
}
