package ru.tchallenge.service.complex

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericComponent
import ru.tchallenge.service.complex.common.GenericWarmer
import ru.tchallenge.service.complex.convention.component.OrchestratorComponent

@CompileStatic
@OrchestratorComponent
class WarmerOrchestrator extends GenericComponent {

    @Autowired(required = false)
    protected BootstrapOrchestrator bootstrapOrchestrator

    @Autowired
    protected Collection<GenericWarmer> warmers

    @Override
    protected void init() {
        warmers.forEach { GenericWarmer it -> it.run() }
    }
}
