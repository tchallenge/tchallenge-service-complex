package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.behavior.value.InvoiceValue
import ru.tchallenge.service.complex.convention.value.Value

@CompileStatic
@Value
class WorkbookInvoice extends InvoiceValue {

    String id
}
