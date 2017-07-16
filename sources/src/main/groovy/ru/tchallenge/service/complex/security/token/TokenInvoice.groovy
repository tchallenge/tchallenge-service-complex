package ru.tchallenge.service.complex.security.token

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInvoiceValue

@CompileStatic
class TokenInvoice extends GenericInvoiceValue {

    String login
    String password
    String voucher
}
