package ru.tchallenge.service.complex.domain.person

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInvoiceValue

@CompileStatic
class PersonInvoice extends GenericInvoiceValue {

    String firstname
    String lastname
    String quickname
}
