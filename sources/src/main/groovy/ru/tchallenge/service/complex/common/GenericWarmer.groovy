package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

@CompileStatic
abstract class GenericWarmer extends GenericComponent {

    abstract void run()
}
