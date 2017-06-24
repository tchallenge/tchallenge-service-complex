package ru.tchallenge.service.complex.common.search

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInvoiceValue

@CompileStatic
class SearchInvoice<T> extends GenericInvoiceValue {

    T filter
    Long limit
    Long offset
}
