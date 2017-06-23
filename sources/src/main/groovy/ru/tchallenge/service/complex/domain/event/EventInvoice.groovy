package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.behavior.value.InvoiceValue
import ru.tchallenge.service.complex.convention.value.Value

@CompileStatic
@Value
class EventInvoice extends InvoiceValue {

    String textcode
}
