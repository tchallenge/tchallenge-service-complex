package ru.tchallenge.service.complex.behavior.value.search

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.behavior.value.InvoiceValue

@CompileStatic
class SearchInvoice<T> extends InvoiceValue {

    T filter
    Long limit
    Long offset
}
