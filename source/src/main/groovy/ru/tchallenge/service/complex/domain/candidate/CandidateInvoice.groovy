package ru.tchallenge.service.complex.domain.candidate

import groovy.transform.TypeChecked

import ru.tchallenge.service.complex.validation.constraints.Url

@TypeChecked
class CandidateInvoice {

    @Url
    String github
}
