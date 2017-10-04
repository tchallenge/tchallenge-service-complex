package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalPersisterBean
import ru.tchallenge.service.complex.convention.component.PersisterComponent
import ru.tchallenge.service.complex.domain.event.interval.EventInterval

@CompileStatic
@PackageScope
@PersisterComponent
class EventPersisterBean extends GenericOrdinalPersisterBean<Event> implements EventPersister {

    @Override
    protected void prepare(Event entity) {
        if (!entity.id) {
            entity.id = nextOrdinalSequence('domain.event')
        }
        if (entity.intervals) {
            entity.intervals.forEach { EventInterval it -> it.event = entity }
        }
    }
}
