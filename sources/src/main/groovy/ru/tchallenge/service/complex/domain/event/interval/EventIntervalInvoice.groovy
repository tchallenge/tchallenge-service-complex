package ru.tchallenge.service.complex.domain.event.interval

import java.time.Instant

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInvoiceValue

@CompileStatic
class EventIntervalInvoice extends GenericInvoiceValue {

    Instant since
    Instant until
}
