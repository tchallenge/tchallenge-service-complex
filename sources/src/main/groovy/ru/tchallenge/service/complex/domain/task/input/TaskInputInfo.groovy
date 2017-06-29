package ru.tchallenge.service.complex.domain.task.input

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInvoiceValue

@CompileStatic
class TaskInputInfo extends GenericInvoiceValue {

    String content
    Boolean regex
    Integer stance
}
