package ru.tchallenge.service.complex.common.search

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInvoiceValue

@CompileStatic
abstract class GenericSearchInvoice extends GenericInvoiceValue {

    Boolean orderDescending = false
    Collection<String> orderProperties = []
    Integer pageNumber = 1
    Integer pageSize = 10
}
