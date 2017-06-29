package ru.tchallenge.service.complex.domain.task.option

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInvoiceValue

@CompileStatic
class TaskOptionInfo extends GenericInvoiceValue {

    String content
    Boolean correct
    String textcode
}
