package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericWarmer
import ru.tchallenge.service.complex.convention.component.WarmerComponent

@CompileStatic
@WarmerComponent
class EventWarmer extends GenericWarmer {

    @Autowired
    protected EventService eventService

    @Override
    void run() {
        eventService.allCategories
        eventService.allStatuses
        eventService.search(new EventSearchInvoice(
                pageNumber: 1,
                pageSize: 1000
        ))
    }
}
