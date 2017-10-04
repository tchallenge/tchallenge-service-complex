package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

@CompileStatic
interface GenericContextConfigurer<T> extends GenericContext<T> {

    void setValue(T value)
}
