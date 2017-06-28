package ru.tchallenge.service.complex.domain.event.interval

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericMapper
import ru.tchallenge.service.complex.convention.component.MapperComponent

@CompileStatic
@MapperComponent
class EventIntervalMapper extends GenericMapper {

    EventInterval asEntity(EventIntervalInvoice invoice) {
        return new EventInterval(
                since: invoice.since,
                until: invoice.until
        )
    }

    EventIntervalInfo asInfo(EventInterval entity) {
        return new EventIntervalInfo(
                since: entity.since,
                until: entity.until
        )
    }
}
