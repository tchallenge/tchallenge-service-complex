package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericWarmerBean
import ru.tchallenge.service.complex.convention.component.WarmerComponent

@CompileStatic
@PackageScope
@WarmerComponent
class EventWarmerBean extends GenericWarmerBean {

    private static final Integer PAGE_COUNT = 10
    private static final Integer PAGE_SIZE = 10

    @Autowired
    EventService eventService

    @Override
    void run() {
        PAGE_COUNT.times { int page -> searchByPage(page + 1) }
    }

    private void searchByPage(int page) {
        eventService.search(new EventSearchInvoice(
                pageNumber: page,
                pageSize: PAGE_SIZE
        ))
    }
}
