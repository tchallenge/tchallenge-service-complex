package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.common.GenericRouter
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteGet
import ru.tchallenge.service.complex.convention.routing.RoutePatch
import ru.tchallenge.service.complex.convention.routing.RoutePost
import ru.tchallenge.service.complex.convention.security.NoAuthentication

@CompileStatic
@RouterComponent("/events")
class EventRouter extends GenericRouter {

    @Autowired
    protected EventFacade eventFacade

    @RoutePost
    EventInfo create(@RequestBody EventInvoice invoice) {
        return eventFacade.create(invoice)
    }

    @NoAuthentication
    @RouteGet("/{textcode}")
    EventInfo getByTextcode(@PathVariable("textcode") String textcode) {
        return eventFacade.get(textcode)
    }

    @RouteGet
    SearchInfo<EventInfo> search(EventSearchInvoice invoice) {
        return eventFacade.search(ensureOrderingAndPaging(invoice))
    }

    @RoutePatch
    EventInfo update(@RequestBody EventInvoice invoice) {
        return eventFacade.update(invoice)
    }

    private static EventSearchInvoice ensureOrderingAndPaging(EventSearchInvoice invoice) {
        return invoice.with {
            orderDescending = invoice.orderDescending ?: false
            orderProperties = invoice.orderProperties ?: [] as Collection<String>
            pageNumber = invoice.pageNumber ?: 1
            pageSize = invoice.pageSize ?: 10
            it
        }
    }
}
