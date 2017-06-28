package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.common.ordinal.sequence.OrdinalSequenceService
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.domain.event.interval.EventInterval

@CompileStatic
@ServiceComponent
class EventPersister extends GenericService {

    @Autowired
    protected EventRepository repository

    @Autowired
    protected OrdinalSequenceService ordinalSequenceService

    Event save(Event entity) {
        prepare(entity)
        return repository.save(entity)
    }

    protected void prepare(Event entity) {
        if (!entity.id) {
            entity.id = nextOrdinalSequence("domain.event")
        }
        if (entity.intervals) {
            entity.intervals.forEach { EventInterval it -> it.event = entity }
        }
    }

    protected Long nextOrdinalSequence(String sequenceId) {
        return ordinalSequenceService.nextValue(sequenceId)
    }
}
