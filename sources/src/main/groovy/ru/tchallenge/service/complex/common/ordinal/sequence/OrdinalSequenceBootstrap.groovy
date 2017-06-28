package ru.tchallenge.service.complex.common.ordinal.sequence

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericBootstrap
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@BootstrapComponent
class OrdinalSequenceBootstrap extends GenericBootstrap<OrdinalSequence, String> {

    @Override
    protected Collection<OrdinalSequence> entities() {
        return [
                new OrdinalSequence(
                        id: "domain.account",
                        currentValue: 0L
                ),
                new OrdinalSequence(
                        id: "domain.event",
                        currentValue: 0L
                ),
                new OrdinalSequence(
                        id: "domain.workbook",
                        currentValue: 0L
                )
        ]
    }
}
