package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@CompileStatic
@RestController
@RequestMapping(path = "/events")
class EventRouter {

    @RequestMapping(method = RequestMethod.POST)
    def create(@RequestBody EventInvoice invoice) {
        return new EventInfo(textcode: invoice.textcode)
    }

    @RequestMapping(path = "/{textcode}", method = RequestMethod.GET)
    def getByTextcode(@PathVariable("textcode") String textcode) {
        return new EventInfo(textcode: textcode)
    }

    @RequestMapping(method = RequestMethod.GET)
    def search(EventSearchInvoice invoice) {
        return [
                new EventInfo(),
                new EventInfo(),
                new EventInfo()
        ]
    }

    @RequestMapping(method = RequestMethod.PUT)
    def update(@RequestBody EventInvoice invoice) {
        return new EventInfo(textcode: invoice.textcode)
    }
}
