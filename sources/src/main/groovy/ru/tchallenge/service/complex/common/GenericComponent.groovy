package ru.tchallenge.service.complex.common

import javax.annotation.PostConstruct

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.utility.miscellaneous.Essentials

@CompileStatic
abstract class GenericComponent implements Essentials {

    @PostConstruct
    protected void init() {

    }
}
