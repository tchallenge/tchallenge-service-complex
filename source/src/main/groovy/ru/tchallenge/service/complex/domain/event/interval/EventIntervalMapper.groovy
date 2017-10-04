package ru.tchallenge.service.complex.domain.event.interval

import groovy.transform.CompileStatic

@CompileStatic
interface EventIntervalMapper {

    EventInterval asEntity(EventIntervalInvoice invoice)

    EventIntervalInfo asInfo(EventInterval entity)
}
