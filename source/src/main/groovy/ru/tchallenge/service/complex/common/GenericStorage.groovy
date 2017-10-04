package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

@CompileStatic
interface GenericStorage<V, K extends Serializable> {

    Optional<V> get(K key)

    Optional<V> put(K key, V value)

    void remove(K... keys)
}
