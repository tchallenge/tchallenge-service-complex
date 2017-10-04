package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

@CompileStatic
abstract class GenericContextConfigurerBean<T> extends GenericComponentBean implements GenericContextConfigurer<T> {

    private volatile T value

    @Override
    Optional<T> getValue() {
        Optional.ofNullable(value)
    }

    @Override
    void setValue(T value) {
        this.value = value
    }
}
