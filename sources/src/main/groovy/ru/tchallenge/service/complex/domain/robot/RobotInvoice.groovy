package ru.tchallenge.service.complex.domain.robot

import groovy.transform.TypeChecked

import javax.validation.Valid

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice

@TypeChecked
class RobotInvoice {

    @Valid
    Collection<EnumeratedInvoice> roles
}
