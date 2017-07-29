package ru.tchallenge.service.complex.domain.sample

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@PackageScope
@ServiceComponent
class SampleServiceBean extends GenericServiceBean implements SampleService {

    @Override
    SampleInfo create(SampleInvoice invoice) {
        new SampleInfo(
                title: invoice.title,
                description: invoice.description
        )
    }

    @Override
    SampleInfo getById(String id) {
        new SampleInfo(
                id: id
        )
    }

    @Override
    SampleInfo update(SampleInvoice invoice) {
        new SampleInfo(
                id: invoice.id,
                title: invoice.title,
                description: invoice.description
        )
    }

    @Override
    SampleInfo updateStatus(SampleInvoice invoice) {
        new SampleInfo(
                id: invoice.id,
                title: invoice.title,
                description: invoice.description
        )
    }
}
