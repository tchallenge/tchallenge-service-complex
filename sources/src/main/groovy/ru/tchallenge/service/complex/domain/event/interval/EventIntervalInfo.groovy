package ru.tchallenge.service.complex.domain.event.interval

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import java.time.Instant

@CompileStatic
@Immutable(knownImmutableClasses = [Instant])
class EventIntervalInfo {

    Instant since
    Instant until
}
