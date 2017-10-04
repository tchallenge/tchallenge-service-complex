package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder

import ru.tchallenge.service.complex.validation.GenericInvoiceValidator

@CompileStatic
abstract class GenericRouterBean extends GenericComponentBean {

    @Autowired
    Collection<GenericInvoiceValidator> validators

    @InitBinder
    void dataBinding(WebDataBinder binder) {
        validators.each { binder.addValidators(it) }
    }
}
