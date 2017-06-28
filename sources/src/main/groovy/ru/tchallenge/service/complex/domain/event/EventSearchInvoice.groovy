package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.search.GenericSearchInvoice

@CompileStatic
class EventSearchInvoice extends GenericSearchInvoice {

    String filterTextPattern
    Collection<String> filterStatusTextcodes
}
