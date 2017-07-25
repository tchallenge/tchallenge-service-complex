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

        // NOTE: textcode validation
        def $textcode = invoice.textcode
        if (!$textcode) {
            throw missing('textcode')
        }
        def $min = 1
        def $max = 16
        if ($textcode.length() < $min || $textcode.length() > $max) {
            throw illegalLength('textcode', $textcode, $min, $max)
        }
        def $alphanumericPattern = '.*'
        if (!matches($textcode, $alphanumericPattern)) {
            throw notMatchPatternOfAlphanumeric('textcode', $textcode)
        }

        // NOTE: title validation
        if (!invoice.title) {
            throw missing('title')
        }
        if ($textcode.length() < 1 || $textcode.length() > 16) {
            throw missing('textcode')
        }

        // other checks connected to title (f.e. length)...
        if (!invoice.greeting) {
            throw missing('greeting')
        }
        // other checks connected to greeting (f.e. length)...
        if (!invoice.intervals) {
            throw missing('intervals')
        }
        if (!invoice.intervals.empty) {
            throw missing('intervals')
        }
        def $intervalIndex = 0
        for (def $interval : invoice.intervals) {
            if (!$interval.since) {
                throw missing("intervals[${$intervalIndex}].since")
            }
            if (!$interval.until) {
                throw missing("intervals[${$intervalIndex}].until")
            }
            if ($interval.since >= $interval.until) {
                throw missing("intervals[${$intervalIndex}].until")
            }
        }
        def $normalizedInvoice = invoice.with {
            id = null
            status = initialStatus()
            it
        }
        def $event = eventMapper.asEntity($normalizedInvoice)
        // TODO: implement pre-persist validation
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

    private ViolationException illegalLength(String path, Object value, int min, int max) {
        throw new UnsupportedOperationException()
    }

    private ViolationException illegalNumberOfItems(String path, int number, int min, int max) {
        throw new UnsupportedOperationException()
    }

    private ViolationException notMatchPattern(String path, Object value, String pattern) {
        throw new UnsupportedOperationException()
    }

    private ViolationException notMatchPatternOfAlphanumeric(String path, Object value) {
        throw new UnsupportedOperationException()
    }

    private boolean matches(String value, String pattern) {
        throw new UnsupportedOperationException()
    }

    private ViolationException eventNotFound(String identifierName, Object identifierValue) {
        def $violation = ResourceViolationInfo.notFound('event', identifierName, identifierValue)
        new ResourceViolationException(this.class, $violation)
    }

    private static EnumeratedInvoice initialStatus() {
        enumerateds.invoice('CREATED')
    }
}
