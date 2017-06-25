package ru.tchallenge.service.complex.security.authentication

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInvoiceValue

@CompileStatic
class AuthenticationInvoice extends GenericInvoiceValue {

    String login
    String password
}
