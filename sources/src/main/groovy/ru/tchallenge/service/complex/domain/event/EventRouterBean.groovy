package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.common.GenericRouterBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteGet
import ru.tchallenge.service.complex.convention.routing.RoutePatch
import ru.tchallenge.service.complex.convention.routing.RoutePost
import ru.tchallenge.service.complex.convention.security.NoAuthentication

@CompileStatic
@RouterComponent('/events')
class EventRouterBean extends GenericRouterBean {

    @Autowired
    EventFacade eventFacade

    @RoutePost
    EventInfo create(@RequestBody EventInvoice invoice) {
        eventFacade.create(invoice)
    }

    @RouteGet('/categories')
    Collection<EnumeratedInfo> getAllCategories() {
        eventFacade.allCategories
    }

    @RouteGet('/statuses')
    Collection<EnumeratedInfo> getAllStatuses() {
        eventFacade.allStatuses
    }

    @NoAuthentication
    @RouteGet('/{textcode}')
    EventInfo getByTextcode(@PathVariable('textcode') String textcode) {
        eventFacade.getByTextcode(textcode)
    }

    @RouteGet
    SearchInfo<EventInfo> search(EventSearchInvoice invoice) {
        eventFacade.search(invoice)
    }

    @RoutePatch
    EventInfo update(@RequestBody EventInvoice invoice) {
        eventFacade.update(invoice)
    }
}
