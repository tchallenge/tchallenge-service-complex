package ru.tchallenge.service.complex.common

import java.time.Instant

import groovy.transform.CompileStatic

@CompileStatic
abstract class GenericComponent {

    protected static Instant now() {
        return Instant.now()
    }

    protected static String uuid() {
        return UUID.randomUUID().toString()
    }
}
