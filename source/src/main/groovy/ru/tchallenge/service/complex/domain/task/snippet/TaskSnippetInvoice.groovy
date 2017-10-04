package ru.tchallenge.service.complex.domain.task.snippet

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice

@CompileStatic
class TaskSnippetInvoice {

    String content
    Integer stance
    EnumeratedInvoice style
}
