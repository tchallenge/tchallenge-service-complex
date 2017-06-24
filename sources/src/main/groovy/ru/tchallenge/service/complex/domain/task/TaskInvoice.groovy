package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.behavior.value.GenericInvoiceValue
import ru.tchallenge.service.complex.convention.value.Value

@CompileStatic
@Value
class TaskInvoice extends GenericInvoiceValue {

    String id
}
