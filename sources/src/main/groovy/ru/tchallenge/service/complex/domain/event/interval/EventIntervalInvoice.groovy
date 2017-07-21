package ru.tchallenge.service.complex.domain.event.interval

import groovy.transform.CompileStatic

import java.time.Instant

import ru.tchallenge.service.complex.common.GenericInvoiceValue

@CompileStatic
class EventIntervalInvoice extends GenericInvoiceValue {

    Instant since
    Instant until
}
