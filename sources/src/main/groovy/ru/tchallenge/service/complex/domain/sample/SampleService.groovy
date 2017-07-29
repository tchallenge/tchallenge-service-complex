package ru.tchallenge.service.complex.domain.sample

import groovy.transform.CompileStatic

@CompileStatic
interface SampleService {

    SampleInfo create(SampleInvoice invoice)

    SampleInfo getById(String id)

    SampleInfo update(SampleInvoice invoice)

    SampleInfo updateStatus(SampleInvoice invoice)
}
