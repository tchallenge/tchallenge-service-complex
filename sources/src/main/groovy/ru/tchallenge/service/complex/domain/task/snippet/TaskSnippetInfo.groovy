package ru.tchallenge.service.complex.domain.task.snippet

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInvoiceValue
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice

@CompileStatic
class TaskSnippetInfo extends GenericInvoiceValue {

    String content
    Integer stance
    EnumeratedInfo style
}
