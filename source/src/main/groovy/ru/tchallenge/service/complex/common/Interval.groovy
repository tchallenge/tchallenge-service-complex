package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

import java.time.Instant

@CompileStatic
interface Interval {

    Instant getSince()

    Instant getUntil()
}
