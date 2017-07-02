package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.search.GenericSearchInvoice

@CompileStatic
class TaskSearchInvoice extends GenericSearchInvoice {

    String filterTextPattern
    Collection<String> filterStatusTextcodes
}
