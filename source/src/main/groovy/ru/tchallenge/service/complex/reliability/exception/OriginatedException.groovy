package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic

@CompileStatic
abstract class OriginatedException extends RuntimeException {

    private final Class<?> origin

    OriginatedException(Class<?> origin) {
        this.origin = origin
    }

    Class<?> getOrigin() {
        origin
    }
}
