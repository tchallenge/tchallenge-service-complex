package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.search.GenericSearchInvoice

@CompileStatic
class WorkbookSearchInvoice extends GenericSearchInvoice {

    Collection<String> filterEventIds
    Collection<String> filterOwnerIds
    Collection<String> filterStatusTextcodes
}
