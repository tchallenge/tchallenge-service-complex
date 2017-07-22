package ru.tchallenge.service.complex.domain.event.interval

import groovy.transform.CompileStatic

import java.time.Instant

@CompileStatic
class EventIntervalInvoice {

    Instant since
    Instant until
}
