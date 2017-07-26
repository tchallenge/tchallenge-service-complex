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
    SampleInfo create(SampleRawInvoice  invoice) {
        throw new UnsupportedOperationException()
    }

    @Override
    SampleInfo getById(String id) {
        throw new UnsupportedOperationException()
    }

    @Override
    SampleInfo update(SampleRawInvoice  invoice) {
        throw new UnsupportedOperationException()
    }
}
