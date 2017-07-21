package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacadeBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.FacadeComponent

@CompileStatic
@PackageScope
@FacadeComponent
class EventFacadeBean extends GenericFacadeBean implements EventFacade {

    @Autowired
    EventService eventService

    @Override
    EventInfo create(EventInvoice invoice) {
        if (!isAuthenticatedEventmod()) {
            throw unauthorized()
        }
        eventService.create(invoice)
    }

    @Override
    Collection<EnumeratedInfo> getAllCategories() {
        eventService.allCategories
    }

    @Override
    Collection<EnumeratedInfo> getAllStatuses() {
        eventService.allStatuses
    }

    @Override
    EventInfo getByTextcode(String textcode) {
        eventService.getByTextcode(textcode)
    }

    @Override
    SearchInfo<EventInfo> search(EventSearchInvoice invoice) {
        if (!isAuthenticatedEventview()) {
            throw unauthorized()
        }
        eventService.search(invoice)
    }

    @Override
    EventInfo update(EventInvoice invoice) {
        if (!isAuthenticatedEventmod()) {
            throw unauthorized()
        }
        eventService.update(invoice)
    }

    private boolean isAuthenticatedEventmod() {
        authenticatedEmployee('EVENTMOD')
    }

    private boolean isAuthenticatedEventview() {
        authenticatedEmployee('EVENTVIEW') || isAuthenticatedEventmod()
    }
}
