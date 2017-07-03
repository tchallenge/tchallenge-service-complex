package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.domain.event.category.EventCategoryRepository
import ru.tchallenge.service.complex.domain.event.status.EventStatusRepository
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.all
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.invoice
import static ru.tchallenge.service.complex.common.search.SearchTransformations.info
import static ru.tchallenge.service.complex.common.search.SearchTransformations.normalizePattern
import static ru.tchallenge.service.complex.common.search.SearchTransformations.pageable

@CompileStatic
@ServiceComponent
class EventService extends GenericService {

    @Autowired
    protected EventMapper eventMapper

    @Autowired
    protected EventPersister eventPersister

    @Autowired
    protected EventRepository eventRepository

    @Autowired
    protected EventCategoryRepository eventCategoryRepository

    @Autowired
    protected EventStatusRepository eventStatusRepository

    EventInfo create(EventInvoice invoice) {
        def event = eventMapper.asEntity(invoice.with {
            id = null
            status = initialStatus()
            it
        })
        return saveAndInfo(event)
    }

    Collection<EnumeratedInfo> getAllCategories() {
        return all(eventCategoryRepository)
    }

    Collection<EnumeratedInfo> getAllStatuses() {
        return all(eventStatusRepository)
    }

    EventInfo getByTextcode(String textcode) {
        return info(eventByTextcode(textcode))
    }

    SearchInfo<EventInfo> search(EventSearchInvoice invoice) {
        def eventPage = eventRepository.findPage(
                normalizePattern(invoice.filterTextPattern),
                invoice.filterStatusTextcodes,
                pageable(invoice)
        )
        return info(invoice, eventPage) {
            Event it -> info(it)
        }
    }

    EventInfo update(EventInvoice invoice) {
        def event = eventById(invoice.id)
        def trimmedInvoice = invoice.with {
            id = null
            it
        }
        def updatedEvent = eventMapper.asEntity(event, trimmedInvoice)
        return saveAndInfo(updatedEvent)
    }

    private Event eventById(String id) {
        def event = eventRepository.findById(id as Long)
        if (!event) {
            throw unknownEvent()
        }
        return event
    }

    private Event eventByTextcode(String textcode) {
        def event = eventRepository.findByTextcode(textcode)
        if (!event) {
            throw unknownEvent()
        }
        return event
    }

    private EventInfo info(Event event) {
        return eventMapper.asInfo(event)
    }

    private Event save(Event event) {
        return eventPersister.save(event)
    }

    private EventInfo saveAndInfo(Event event) {
        return info(save(event))
    }

    private static EnumeratedInvoice initialStatus() {
        return invoice("CREATED")
    }

    private static RuntimeException unknownEvent() {
        return new RuntimeException("referenced event does not exist")
    }
}
