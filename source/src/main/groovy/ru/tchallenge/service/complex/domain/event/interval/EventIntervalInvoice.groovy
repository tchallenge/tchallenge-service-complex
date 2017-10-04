package ru.tchallenge.service.complex.domain.event.interval

import groovy.transform.CompileStatic

import java.time.Instant

import ru.tchallenge.service.complex.common.Interval
import ru.tchallenge.service.complex.validation.constraints.Required
import ru.tchallenge.service.complex.validation.constraints.TimeInterval

@CompileStatic
@TimeInterval
class EventIntervalInvoice implements Interval {

    @Required
    Instant since

    @Required
    Instant until
}
