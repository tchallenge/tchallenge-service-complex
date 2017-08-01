package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.reliability.exception.ContractViolationException
import ru.tchallenge.service.complex.reliability.exception.ResourceViolationException
import ru.tchallenge.service.complex.reliability.exception.ViolationException
import ru.tchallenge.service.complex.reliability.violation.ContractViolationInfo
import ru.tchallenge.service.complex.reliability.violation.ResourceViolationInfo

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

    @Override
    EventInfo create(EventInvoice invoice) {
        def $existingEvent = eventRepository.findByTextcode(invoice.textcode)
        if ($existingEvent) {
            throw new RuntimeException("event with textcode ${invoice.textcode} already exists")
        }
        def $normalizedInvoice = invoice.with {
            id = null
            status = initialStatus()
            it
        }
        def $event = eventMapper.asEntity($normalizedInvoice)
        def $result = saveAndInfo($event)
        logAsInfo('New event has been created', $event)
        $result
    }

    @Override
    EventInfo getByTextcode(String textcode) {
        def $event = eventByTextcode(textcode)
        info($event)
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
        def $normalizedInvoice = invoice.with {
            id = null
            it
        }
        def $updatedEvent = eventMapper.asEntity($event, $normalizedInvoice)
        // TODO: implement pre-update validation
        def $result = saveAndInfo($updatedEvent)
        logAsInfo('Event has been updated', $result)
        $result
    }

    private Event eventById(String id) {
        def $result = eventRepository.findById(id as Long)
        if (!$result) {
            throw eventNotFound('id', id)
        }
        $result
    }

    private Event eventByTextcode(String textcode) {
        def $result = eventRepository.findByTextcode(textcode)
        if (!$result) {
            throw eventNotFound('textcode', textcode)
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

    private ViolationException missing(String path) {
        def $violation = ContractViolationInfo.missing(path)
        new ContractViolationException(this.class, $violation)
    }

    private ViolationException eventNotFound(String identifierName, Object identifierValue) {
        def $violation = ResourceViolationInfo.notFound('event', identifierName, identifierValue)
        new ResourceViolationException(this.class, $violation)
    }

    private static EnumeratedInvoice initialStatus() {
        enumerateds.invoice('CREATED')
    }
}
