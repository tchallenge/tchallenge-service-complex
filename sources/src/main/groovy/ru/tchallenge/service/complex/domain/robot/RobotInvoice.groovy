package ru.tchallenge.service.complex.domain.robot

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInvoiceValue
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice

@CompileStatic
class RobotInvoice extends GenericInvoiceValue {

    Collection<EnumeratedInvoice> roles
}
