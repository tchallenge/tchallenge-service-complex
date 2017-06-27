package ru.tchallenge.service.complex.common.search

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInvoiceValue

@CompileStatic
abstract class GenericSearchInvoice extends GenericInvoiceValue {

    Boolean orderDescending
    Collection<String> orderProperties
    Integer pageNumber
    Integer pageSize
}
