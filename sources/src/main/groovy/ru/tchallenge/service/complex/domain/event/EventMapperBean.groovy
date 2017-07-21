package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent
import ru.tchallenge.service.complex.domain.event.category.EventCategoryRepository
import ru.tchallenge.service.complex.domain.event.interval.EventInterval
import ru.tchallenge.service.complex.domain.event.interval.EventIntervalInfo
import ru.tchallenge.service.complex.domain.event.interval.EventIntervalInvoice
import ru.tchallenge.service.complex.domain.event.interval.EventIntervalMapper
import ru.tchallenge.service.complex.domain.event.status.EventStatusRepository

@CompileStatic
@PackageScope
@MapperComponent
class EventMapperBean extends GenericMapperBean implements EventMapper {

    @Autowired
    EventIntervalMapper eventIntervalMapper

    @Autowired
    EventCategoryRepository eventCategoryRepository

    @Autowired
    EventStatusRepository eventStatusRepository

    @Override
    Event asEntity(EventInvoice invoice) {
        asEntity(null, invoice)
    }

    @Override
    Event asEntity(Event entity, EventInvoice invoice) {
        def $result = entity ?: new Event()
        $result.with {
            id = invoice.id as Long ?: id
            textcode = invoice.textcode ?: textcode
            title = invoice.title ?: title
            subtitle = invoice.subtitle ?: subtitle
            description = invoice.description ?: description
            greeting = invoice.greeting ?: greeting
            intervals = invoice.intervals ? mapIntervalInvoices(invoice.intervals) : intervals
            category = invoice.category ? enumerateds.one(eventCategoryRepository, invoice.category) : category
            status = invoice.status ? enumerateds.one(eventStatusRepository, invoice.status) : status
            it
        }
    }

    @Override
    EventInfo asInfo(Event entity) {
        new EventInfo(
                id: entity.id as String,
                textcode: entity.textcode,
                title: entity.title,
                subtitle: entity.subtitle,
                description: entity.description,
                greeting: entity.greeting,
                intervals: mapIntervals(entity.intervals),
                category: enumerateds.info(entity.category),
                status: enumerateds.info(entity.status)
        )
    }

    private Collection<EventIntervalInfo> mapIntervals(Collection<EventInterval> entities) {
        foundamentals.mapCollection(entities) { EventInterval it -> eventIntervalMapper.asInfo(it) }
    }

    private Collection<EventInterval> mapIntervalInvoices(Collection<EventIntervalInvoice> invoices) {
        foundamentals.mapCollection(invoices) { EventIntervalInvoice it -> eventIntervalMapper.asEntity(it) }
    }
}
