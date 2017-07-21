package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.search.SearchInfo

@CompileStatic
interface EventFacade {

    EventInfo create(EventInvoice invoice)

    Collection<EnumeratedInfo> getAllCategories()

    Collection<EnumeratedInfo> getAllStatuses()

    EventInfo getByTextcode(String textcode)

    SearchInfo<EventInfo> search(EventSearchInvoice invoice)

    EventInfo update(EventInvoice invoice)
}
