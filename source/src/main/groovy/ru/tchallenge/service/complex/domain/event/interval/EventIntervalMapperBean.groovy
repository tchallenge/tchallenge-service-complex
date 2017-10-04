package ru.tchallenge.service.complex.domain.event.interval

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent

@CompileStatic
@PackageScope
@MapperComponent
class EventIntervalMapperBean extends GenericMapperBean implements EventIntervalMapper {

    @Override
    EventInterval asEntity(EventIntervalInvoice invoice) {
        new EventInterval(
                since: invoice.since,
                until: invoice.until
        )
    }

    @Override
    EventIntervalInfo asInfo(EventInterval entity) {
        new EventIntervalInfo(
                since: entity.since,
                until: entity.until
        )
    }
}
