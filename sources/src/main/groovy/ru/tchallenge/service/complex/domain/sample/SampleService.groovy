package ru.tchallenge.service.complex.domain.sample

import groovy.transform.CompileStatic

@CompileStatic
interface SampleService {

    SampleInfo create(SampleRawInvoice  invoice)

    SampleInfo getById(String id)

    SampleInfo update(SampleRawInvoice invoice)
}
