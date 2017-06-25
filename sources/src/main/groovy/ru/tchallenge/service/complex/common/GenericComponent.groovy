package ru.tchallenge.service.complex.common

import java.time.Instant

import groovy.transform.CompileStatic

@CompileStatic
abstract class GenericComponent {

    static Instant now() {
        return Instant.now()
    }

    static String uuid() {
        return UUID.randomUUID().toString()
    }
}
