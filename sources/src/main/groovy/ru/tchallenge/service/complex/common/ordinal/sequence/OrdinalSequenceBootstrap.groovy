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
                ordinalSequence("domain.account"),
                ordinalSequence("domain.event"),
                ordinalSequence("domain.task"),
                ordinalSequence("domain.workbook")
        ]
    }

    private static OrdinalSequence ordinalSequence(String id) {
        return new OrdinalSequence(
                id: id,
                initialValue: 1L,
                step: 1
        )
    }
}
