package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice
import ru.tchallenge.service.complex.domain.account.AccountInvoice
import ru.tchallenge.service.complex.domain.event.EventInvoice

@CompileStatic
class WorkbookInvoice {

    String id
    EventInvoice event
    AccountInvoice owner
    EnumeratedInvoice maturity
    EnumeratedInvoice specialization
    EnumeratedInvoice status
}
