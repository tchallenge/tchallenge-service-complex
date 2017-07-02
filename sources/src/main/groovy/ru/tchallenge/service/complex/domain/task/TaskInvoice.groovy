package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInvoiceValue
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice
import ru.tchallenge.service.complex.domain.task.input.TaskInputInvoice
import ru.tchallenge.service.complex.domain.task.option.TaskOptionInvoice
import ru.tchallenge.service.complex.domain.task.snippet.TaskSnippetInvoice

@CompileStatic
class TaskInvoice extends GenericInvoiceValue {

    String id

    String title
    String introduction
    String question

    Collection<TaskInputInvoice> inputs
    Collection<TaskOptionInvoice> options
    Collection<TaskSnippetInvoice> snippets

    Collection<EnumeratedInvoice> categories
    EnumeratedInvoice difficulty
    EnumeratedInvoice expectation
    EnumeratedInvoice status
}
