package ru.tchallenge.service.complex

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericBootstrap
import ru.tchallenge.service.complex.common.GenericOrchestratorBean
import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity
import ru.tchallenge.service.complex.common.ordinal.sequence.OrdinalSequenceBootstrap
import ru.tchallenge.service.complex.convention.component.BootstrapComponent
import ru.tchallenge.service.complex.convention.component.OrchestratorComponent
import ru.tchallenge.service.complex.domain.account.AccountBootstrap
import ru.tchallenge.service.complex.domain.event.EventBootstrap
import ru.tchallenge.service.complex.domain.task.TaskBootstrap

@CompileStatic
@PackageScope
// TODO: replace BootstrapComponent with profile
@BootstrapComponent
@OrchestratorComponent
class BootstrapOrchestratorBean extends GenericOrchestratorBean {

    @Autowired
    OrdinalSequenceBootstrap ordinalSequenceBootstrap

    @Autowired
    Collection<GenericBootstrap<? extends GenericEnumeratedEntity, String>> enumeratedBootstraps

    @Autowired
    AccountBootstrap accountBootstrap

    @Autowired
    EventBootstrap eventBootstrap

    @Autowired
    TaskBootstrap taskBootstrap

    @Override
    protected void init() {
        runSequentially(enumeratedBootstraps)
        runSequentially([
                ordinalSequenceBootstrap,
                accountBootstrap,
                eventBootstrap,
                taskBootstrap
        ])
    }
}
