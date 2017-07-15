package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

import java.time.Instant

import ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals

@CompileStatic
abstract class GenericValue implements Serializable {

    protected static Instant getNow() {
        Foundamentals.now
    }
}
