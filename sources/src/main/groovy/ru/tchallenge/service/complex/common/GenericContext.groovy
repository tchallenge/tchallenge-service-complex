package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

@CompileStatic
interface GenericContext<T> {

    Optional<T> getValue()
}
