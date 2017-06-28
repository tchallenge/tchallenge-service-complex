package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacade
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.FacadeComponent

@CompileStatic
@FacadeComponent
class EventFacade extends GenericFacade {

    @Autowired
    protected EventService eventService

    EventInfo create(EventInvoice invoice) {
        if (!isAuthenticatedEventmod()) {
            throw unauthorized()
        }
        return eventService.create(invoice)
    }

    EventInfo get(String textcode) {
        return eventService.get(textcode)
    }

    SearchInfo<EventInfo> search(EventSearchInvoice invoice) {
        if (!isAuthenticatedEventview()) {
            throw unauthorized()
        }
        return eventService.search(invoice)
    }

    EventInfo update(EventInvoice invoice) {
        if (!isAuthenticatedEventmod()) {
            throw unauthorized()
        }
        return eventService.update(invoice)
    }

    private boolean isAuthenticatedEventmod() {
        return authenticatedEmployee("EVENTMOD")
    }

    private boolean isAuthenticatedEventview() {
        return authenticatedEmployee("EVENTVIEW") || isAuthenticatedEventmod()
    }
}
