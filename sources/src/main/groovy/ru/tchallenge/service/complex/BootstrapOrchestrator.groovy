package ru.tchallenge.service.complex

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.Bootstrap
import ru.tchallenge.service.complex.common.GenericOrchestrator
import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity
import ru.tchallenge.service.complex.common.ordinal.sequence.OrdinalSequenceBootstrap
import ru.tchallenge.service.complex.convention.component.BootstrapComponent
import ru.tchallenge.service.complex.convention.component.OrchestratorComponent
import ru.tchallenge.service.complex.domain.account.AccountBootstrap
import ru.tchallenge.service.complex.domain.event.EventBootstrap

@CompileStatic
@BootstrapComponent
@OrchestratorComponent
class BootstrapOrchestrator extends GenericOrchestrator {

    @Autowired
    protected OrdinalSequenceBootstrap ordinalSequenceBootstrap

    @Autowired
    protected Collection<Bootstrap<? extends GenericEnumeratedEntity, String>> enumeratedBootstraps

    @Autowired
    protected AccountBootstrap accountBootstrap

    @Autowired
    protected EventBootstrap eventBootstrap

    @Override
    protected void init() {
        runSequentially(enumeratedBootstraps)
        runSequentially([
                ordinalSequenceBootstrap,
                accountBootstrap,
                eventBootstrap
        ])
    }
}
