package ru.tchallenge.service.complex.behavior.value.search

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.behavior.value.GenericInvoiceValue

@CompileStatic
class SearchInvoice<T> extends GenericInvoiceValue {

    T filter
    Long limit
    Long offset
}
