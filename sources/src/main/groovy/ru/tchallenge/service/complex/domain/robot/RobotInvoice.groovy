package ru.tchallenge.service.complex.domain.robot

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice

@CompileStatic
class RobotInvoice {

    String id
    Collection<EnumeratedInvoice> roles
}
