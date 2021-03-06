package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@CompileStatic
abstract class GenericStorageLocalBean<V, K extends Serializable> extends GenericComponentBean implements GenericStorage<V, K> {

    private final ConcurrentMap<K, V> items = new ConcurrentHashMap<>()

    @Override
    Optional<V> get(K key) {
        return Optional.ofNullable(items.get(key))
    }

    @Override
    Optional<V> put(K key, V value) {
        return Optional.ofNullable(items.put(key, value))
    }

    @Override
    void remove(K... keys) {
        for (def key : keys) {
            items.remove(key)
        }
    }
}
