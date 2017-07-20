package ru.tchallenge.service.complex.common.enumerated

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericBootstrapBean

@CompileStatic
abstract class GenericEnumeratedBootstrapBean<E extends GenericEnumeratedEntity> extends GenericBootstrapBean<E, String> {

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
