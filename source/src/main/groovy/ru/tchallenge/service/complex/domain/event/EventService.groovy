package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.search.SearchInfo

@CompileStatic
interface EventService {

    EventInfo create(EventInvoice invoice)

    EventInfo getByTextcode(String textcode)

    SearchInfo<EventInfo> search(EventSearchInvoice invoice)

    EventInfo update(EventInvoice invoice)
}
