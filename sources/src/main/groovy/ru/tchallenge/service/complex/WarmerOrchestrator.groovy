package ru.tchallenge.service.complex

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericOrchestrator
import ru.tchallenge.service.complex.common.Warmer
import ru.tchallenge.service.complex.convention.component.OrchestratorComponent

@CompileStatic
@OrchestratorComponent
class WarmerOrchestrator extends GenericOrchestrator {

    @Autowired(required = false)
    protected BootstrapOrchestrator bootstrapOrchestrator

    @Autowired
    protected Collection<? extends Warmer> warmers

    @Override
    protected void init() {
        runSequentially(warmers)
    }
}
