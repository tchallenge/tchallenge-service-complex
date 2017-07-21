package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.domain.event.category.EventCategoryRepository
import ru.tchallenge.service.complex.domain.event.status.EventStatusRepository

@CompileStatic
@PackageScope
@ServiceComponent
class EventServiceBean extends GenericServiceBean implements EventService {

    @Autowired
    EventMapper eventMapper

    @Autowired
    EventPersister eventPersister

    @Autowired
    EventRepository eventRepository

    @Autowired
    EventCategoryRepository eventCategoryRepository

    @Autowired
    EventStatusRepository eventStatusRepository

    @Override
    EventInfo create(EventInvoice invoice) {
        def $event = eventMapper.asEntity(invoice.with {
            id = null
            status = initialStatus()
            it
        })
        logAsInfo('New event has been created', $event)
        saveAndInfo($event)
    }

    @Override
    Collection<EnumeratedInfo> getAllCategories() {
        enumerateds.all(eventCategoryRepository)
    }

    @Override
    Collection<EnumeratedInfo> getAllStatuses() {
        enumerateds.all(eventStatusRepository)
    }

    @Override
    EventInfo getByTextcode(String textcode) {
        info(eventByTextcode(textcode))
    }

    @Override
    SearchInfo<EventInfo> search(EventSearchInvoice invoice) {
        def $page = eventRepository.findPage(
                searches.normalizePattern(invoice.filterTextPattern),
                invoice.filterStatusTextcodes,
                searches.pageable(invoice)
        )
        def $result = searches.info(invoice, $page) {
            Event it -> info(it)
        }
        $result
    }

    @Override
    EventInfo update(EventInvoice invoice) {
        def $event = eventById(invoice.id)
        def $trimmedInvoice = invoice.with {
            id = null
            it
        }
        def $updatedEvent = eventMapper.asEntity($event, $trimmedInvoice)
        def $result = saveAndInfo($updatedEvent)
        logAsInfo('Event has been updated', $result)
        $result
    }

    private Event eventById(String id) {
        def $result = eventRepository.findById(id as Long)
        if (!$result) {
            throw unknownEvent()
        }
        $result
    }

    private Event eventByTextcode(String textcode) {
        def $result = eventRepository.findByTextcode(textcode)
        if (!$result) {
            throw unknownEvent()
        }
        $result
    }

    private EventInfo info(Event event) {
        eventMapper.asInfo(event)
    }

    private Event save(Event event) {
        eventPersister.save(event)
    }

    private EventInfo saveAndInfo(Event event) {
        info(save(event))
    }

    private static EnumeratedInvoice initialStatus() {
        enumerateds.invoice('CREATED')
    }

    private static RuntimeException unknownEvent() {
        new RuntimeException('referenced event does not exist')
    }
}
