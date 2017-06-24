package ru.tchallenge.service.complex.domain.employee

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInvoiceValue
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice

@CompileStatic
class EmployeeInvoice extends GenericInvoiceValue {

    Collection<EnumeratedInvoice> roles
}
