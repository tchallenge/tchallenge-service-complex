package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.ServiceComponent
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

    EventInfo create(EventInvoice invoice) {
        def account = eventMapper.asEntity(invoice.with {
            id = null
            status = initialStatus()
            it
        })
        return saveAndInfo(account)
    }

    EventInfo get(String textcode) {
        return info(eventByTextcode(textcode))
    }

    SearchInfo<EventInfo> search(EventSearchInvoice invoice) {
        Page<Event> page = eventRepository.findPage(
                normalizePattern(invoice.filterTextPattern),
                invoice.filterStatusTextcodes,
                pageable(invoice)
        )
        return info(invoice, page) {
            Event it -> info(it)
        }
    }

    EventInfo update(EventInvoice invoice) {
        def entity = eventById(invoice.id)
        def trimmedInvoice = invoice.with {
            id = null
            it
        }
        def mergedEntity = eventMapper.asEntity(entity, trimmedInvoice)
        return saveAndInfo(mergedEntity)
    }

    private Event eventById(String id) {
        def result = eventRepository.findById(id as Long)
        if (!result) {
            throw unknownEvent()
        }
        return result
    }

    private Event eventByTextcode(String textcode) {
        def result = eventRepository.findByTextcode(textcode)
        if (!result) {
            throw unknownEvent()
        }
        return result
    }

    private EventInfo info(Event entity) {
        return eventMapper.asInfo(entity)
    }

    private Event save(Event entity) {
        return eventPersister.save(entity)
    }

    private EventInfo saveAndInfo(Event entity) {
        return info(save(entity))
    }

    private static EnumeratedInvoice initialStatus() {
        return enumeratedInvoice("CREATED")
    }

    private static EnumeratedInvoice enumeratedInvoice(String textcode) {
        return new EnumeratedInvoice(
                textcode: textcode
        )
    }

    private static RuntimeException unknownEvent() {
        return new RuntimeException("referenced event does not exist")
    }
}
