package ru.tchallenge.service.complex.domain.event.interval

import groovy.transform.CompileStatic

import java.time.Instant

import ru.tchallenge.service.complex.common.GenericInfoValue

@CompileStatic
class EventIntervalInfo extends GenericInfoValue {

    Instant since
    Instant until
}
