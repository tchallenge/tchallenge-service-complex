package ru.tchallenge.service.complex.domain.sample

import groovy.transform.CompileStatic

import org.springframework.stereotype.Component
import org.springframework.validation.Errors

import ru.tchallenge.service.complex.validation.GenericInvoiceValidatorBean

@CompileStatic
@Component
class SampleInvoiceValidator extends GenericInvoiceValidatorBean<SampleInvoice> {

    @Override
    protected void validate(Object target, Errors errors, Set<Class<?>> groups) {
        logAsDebug('Sample invoice validator is triggered', target)
    }
}
