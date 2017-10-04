package ru.tchallenge.service.complex.domain.task.snippet

import groovy.transform.CompileStatic

@CompileStatic
interface TaskSnippetMapper {

    TaskSnippet asEntity(TaskSnippetInvoice invoice)

    TaskSnippetInfo asInfo(TaskSnippet entity)
}
