package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.search.GenericSearchInvoice

@CompileStatic
class AccountSearchInvoice extends GenericSearchInvoice {

    String filterEmailPattern
    String filterLoginPattern
    String filterPersonNamePattern
    Collection<String> filterRealmTextcodes
    Collection<String> filterStatusTextcodes
}
