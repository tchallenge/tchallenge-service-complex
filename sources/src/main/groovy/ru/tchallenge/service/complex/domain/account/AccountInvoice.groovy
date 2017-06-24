package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.behavior.value.GenericInvoiceValue
import ru.tchallenge.service.complex.convention.value.Value

@CompileStatic
@Value
class AccountInvoice extends GenericInvoiceValue {

    String id
    String login
}
