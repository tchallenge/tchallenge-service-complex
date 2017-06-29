package ru.tchallenge.service.complex.domain.task.option

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInvoiceValue

@CompileStatic
class TaskOptionInvoice extends GenericInvoiceValue {

    String content
    Boolean correct
    String textcode
}
