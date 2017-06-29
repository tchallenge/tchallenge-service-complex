package ru.tchallenge.service.complex

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericBootstrap
import ru.tchallenge.service.complex.common.GenericComponent
import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrap
import ru.tchallenge.service.complex.common.ordinal.sequence.OrdinalSequenceBootstrap
import ru.tchallenge.service.complex.convention.component.BootstrapComponent
import ru.tchallenge.service.complex.convention.component.OrchestratorComponent
import ru.tchallenge.service.complex.domain.account.AccountBootstrap
import ru.tchallenge.service.complex.domain.event.EventBootstrap

@CompileStatic
@BootstrapComponent
@OrchestratorComponent
class BootstrapOrchestrator extends GenericComponent {

    @Autowired
    protected OrdinalSequenceBootstrap ordinalSequenceBootstrap

    @Autowired
    protected Collection<GenericEnumeratedBootstrap> enumeratedBootstraps

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

    private static void runSequentially(Collection<? extends GenericBootstrap> bootstraps) {
        bootstraps.forEach { GenericBootstrap it -> it.run() }
    }
}
