package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

@CompileStatic
abstract class GenericOrchestratorBean extends GenericComponentBean {

    protected static void runSequentially(Collection<? extends Orchestrated> components) {
        components.forEach { Orchestrated it -> it.run() }
    }
}
