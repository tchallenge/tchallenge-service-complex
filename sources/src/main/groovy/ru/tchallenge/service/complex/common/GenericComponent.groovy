package ru.tchallenge.service.complex.common

import javax.annotation.PostConstruct

import groovy.transform.CompileStatic

@CompileStatic
abstract class GenericComponent {

    @PostConstruct
    protected void init() {

    }
}
