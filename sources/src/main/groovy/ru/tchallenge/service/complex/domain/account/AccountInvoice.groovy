package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.behavior.value.InvoiceValue
import ru.tchallenge.service.complex.convention.value.Value

@CompileStatic
@Value
class AccountInvoice extends InvoiceValue {

    String id
    String login
}
