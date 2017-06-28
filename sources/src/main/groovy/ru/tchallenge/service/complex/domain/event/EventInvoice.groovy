package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInvoiceValue
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice
import ru.tchallenge.service.complex.domain.event.interval.EventIntervalInvoice

@CompileStatic
class EventInvoice extends GenericInvoiceValue {

    String id
    String textcode
    String title
    String subtitle
    String description
    String greeting
    Collection<EventIntervalInvoice> intervals
    EnumeratedInvoice category
    EnumeratedInvoice status
}
