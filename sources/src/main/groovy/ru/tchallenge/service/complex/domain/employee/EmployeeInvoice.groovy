package ru.tchallenge.service.complex.domain.employee

import groovy.transform.TypeChecked

import javax.validation.Valid

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice

@TypeChecked
class EmployeeInvoice {

    @Valid
    Collection<EnumeratedInvoice> roles
}
