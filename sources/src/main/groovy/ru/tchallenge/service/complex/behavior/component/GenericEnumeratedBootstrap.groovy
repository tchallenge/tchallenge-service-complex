package ru.tchallenge.service.complex.behavior.component

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.behavior.entity.GenericEnumeratedEntity

@CompileStatic
abstract class GenericEnumeratedBootstrap<E extends GenericEnumeratedEntity> extends GenericBootstrap<E, String> {

    @Override
    protected Collection<E> entities() {
        def result = enumeratedEntities()
        def stance = 1
        for (E entity : result) {
            entity.id = entity.textcode
            entity.stance = stance++
        }
        return result
    }

    protected abstract Collection<E> enumeratedEntities()
}
