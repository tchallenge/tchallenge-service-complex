package ru.tchallenge.service.complex.common.search

import groovy.transform.CompileStatic

@CompileStatic
abstract class GenericSearchInvoice {

    Boolean orderDescending = false
    Collection<String> orderProperties = []
    Integer pageNumber = 1
    Integer pageSize = 10
}
