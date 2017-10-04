package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

@CompileStatic
interface EventMapper {

    Event asEntity(EventInvoice invoice)

    Event asEntity(Event entity, EventInvoice invoice)

    EventInfo asInfo(Event entity)
}
