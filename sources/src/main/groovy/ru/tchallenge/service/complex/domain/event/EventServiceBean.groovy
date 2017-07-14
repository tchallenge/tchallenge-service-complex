package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

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
class EventServiceBean extends GenericService implements EventService {

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

    @Override
    EventInfo create(EventInvoice invoice) {
        def event = eventMapper.asEntity(invoice.with {
            id = null
            status = initialStatus()
            it
        })
        logAsInfo("New event has been created", event)
        return saveAndInfo(event)
    }

    @Override
    Collection<EnumeratedInfo> getAllCategories() {
        return all(eventCategoryRepository)
    }

    @Override
    Collection<EnumeratedInfo> getAllStatuses() {
        return all(eventStatusRepository)
    }

    @Override
    EventInfo getByTextcode(String textcode) {
        return info(eventByTextcode(textcode))
    }

    @Override
    SearchInfo<EventInfo> search(EventSearchInvoice invoice) {
        def eventPage = eventRepository.findPage(
                normalizePattern(invoice.filterTextPattern),
                invoice.filterStatusTextcodes,
                pageable(invoice)
        )
        def searchInfo = info(invoice, eventPage) {
            Event it -> info(it)
        }
        logAsDebug("Search result of events is prepared", searchInfo)
        return searchInfo
    }

    @Override
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
