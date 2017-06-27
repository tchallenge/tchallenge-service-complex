package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInvoiceValue

@CompileStatic
class AccountFilterInvoice extends GenericInvoiceValue {

    String emailPattern
    String loginPattern
    String personNamePattern
    Collection<String> realmTextcodes
    Collection<String> statusTextcodes
}
