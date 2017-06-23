package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.behavior.component.Router
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteGet
import ru.tchallenge.service.complex.convention.routing.RoutePost
import ru.tchallenge.service.complex.convention.routing.RoutePut

@CompileStatic
@RouterComponent("/events")
class EventRouter implements Router {

    @RoutePost
    def create(@RequestBody EventInvoice invoice) {
        return new EventInfo(textcode: invoice.textcode)
    }

    @RouteGet("/{textcode}")
    def getByTextcode(@PathVariable("textcode") String textcode) {
        return new EventInfo(textcode: textcode)
    }

    @RouteGet()
    def search(EventSearchInvoice invoice) {
        return [
                new EventInfo(),
                new EventInfo(),
                new EventInfo()
        ]
    }

    @RoutePut
    def update(@RequestBody EventInvoice invoice) {
        return new EventInfo(textcode: invoice.textcode)
    }
}