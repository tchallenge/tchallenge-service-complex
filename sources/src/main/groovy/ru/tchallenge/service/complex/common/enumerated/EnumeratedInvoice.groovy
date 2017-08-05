package ru.tchallenge.service.complex.common.enumerated

import groovy.transform.TypeChecked

import ru.tchallenge.service.complex.validation.constraints.Textcode

@TypeChecked
class EnumeratedInvoice {

    @Textcode
    String textcode
}
