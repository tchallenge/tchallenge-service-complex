package ru.tchallenge.service.complex.common

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

import groovy.transform.CompileStatic

@CompileStatic
abstract class GenericStorageLocal<V extends GenericInfoValue, K extends Serializable>
        extends GenericComponent
        implements GenericStorage<V, K> {

    private final ConcurrentMap<K, V> items = new ConcurrentHashMap<>()

    @Override
    Optional<V> get(K key) {
        return Optional.of(items.get(key))
    }

    @Override
    Optional<V> put(K key, V value) {
        return Optional.of(items.put(key, value))
    }

    @Override
    void remove(K... keys) {
        for (def key : keys) {
            items.remove(key)
        }
    }
}
