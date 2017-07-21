package ru.tchallenge.service.complex.common.ordinal.sequence

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.GenericBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@PackageScope
@BootstrapComponent
class OrdinalSequenceBootstrapBean extends GenericBootstrapBean<OrdinalSequence, String> implements OrdinalSequenceBootstrap {

    @Override
    protected Collection<OrdinalSequence> entities() {
        [
                ordinalSequence('domain.account'),
                ordinalSequence('domain.event'),
                ordinalSequence('domain.task'),
                ordinalSequence('domain.workbook')
        ]
    }

    private static OrdinalSequence ordinalSequence(String id) {
        new OrdinalSequence(
                id: id,
                description: "ordinal sequence for ${id} entity",
                initialValue: 1L,
                step: 1
        )
    }
}
