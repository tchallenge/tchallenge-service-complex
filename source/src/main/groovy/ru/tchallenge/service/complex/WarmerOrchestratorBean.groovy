package ru.tchallenge.service.complex

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericOrchestratorBean
import ru.tchallenge.service.complex.common.GenericWarmer
import ru.tchallenge.service.complex.convention.component.OrchestratorComponent

@CompileStatic
@PackageScope
@OrchestratorComponent
class WarmerOrchestratorBean extends GenericOrchestratorBean {

    // TODO: implement non-injection dependency (init order)
    @Autowired(required = false)
    BootstrapOrchestratorBean bootstrapOrchestrator

    @Autowired
    Collection<? extends GenericWarmer> warmers

    @Override
    protected void init() {
        runSequentially(warmers)
    }
}
