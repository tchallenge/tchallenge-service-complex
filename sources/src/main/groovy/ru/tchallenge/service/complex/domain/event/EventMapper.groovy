package ru.tchallenge.service.complex.domain.event

import java.util.stream.Collectors

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericMapper
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.*
import ru.tchallenge.service.complex.convention.component.MapperComponent
import ru.tchallenge.service.complex.domain.event.category.EventCategoryRepository
import ru.tchallenge.service.complex.domain.event.interval.EventInterval
import ru.tchallenge.service.complex.domain.event.interval.EventIntervalInfo
import ru.tchallenge.service.complex.domain.event.interval.EventIntervalInvoice
import ru.tchallenge.service.complex.domain.event.interval.EventIntervalMapper
import ru.tchallenge.service.complex.domain.event.status.EventStatusRepository

@CompileStatic
@MapperComponent
class EventMapper extends GenericMapper {

    @Autowired
    protected EventIntervalMapper eventIntervalMapper

    @Autowired
    protected EventCategoryRepository eventCategoryRepository

    @Autowired
    protected EventStatusRepository eventStatusRepository

    Event asEntity(EventInvoice invoice) {
        return asEntity(null, invoice)
    }

    Event asEntity(Event entity, EventInvoice invoice) {
        entity = entity ?: new Event()
        return entity.with {
            id = invoice.id as Long ?: id
            textcode = invoice.textcode ?: textcode
            title = invoice.title ?: title
            subtitle = invoice.subtitle ?: subtitle
            description = invoice.description ?: description
            greeting = invoice.greeting ?: greeting
            intervals = invoice.intervals ? mapIntervalInvoices(invoice.intervals) : intervals
            category = invoice.category ? one(eventCategoryRepository, invoice.category) : category
            status = invoice.status ? one(eventStatusRepository, invoice.status) : status
            it
        }
    }

    EventInfo asInfo(Event entity) {
        return new EventInfo(
                id: entity.id as String,
                textcode: entity.textcode,
                title: entity.title,
                subtitle: entity.subtitle,
                description: entity.description,
                greeting: entity.greeting,
                intervals: mapIntervals(entity.intervals),
                category: info(entity.category),
                status: info(entity.status)
        )
    }

    private Collection<EventIntervalInfo> mapIntervals(Collection<EventInterval> entities) {
        return entities
                .stream()
                .map { EventInterval it -> eventIntervalMapper.asInfo(it) }
                .collect(Collectors.toList())
    }

    private Collection<EventInterval> mapIntervalInvoices(Collection<EventIntervalInvoice> invoices) {
        return invoices
                .stream()
                .map { EventIntervalInvoice it -> eventIntervalMapper.asEntity(it) }
                .collect(Collectors.toList())
    }
}
