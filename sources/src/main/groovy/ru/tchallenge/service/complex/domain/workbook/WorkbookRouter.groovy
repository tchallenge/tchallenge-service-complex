package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.behavior.component.Router
import ru.tchallenge.service.complex.behavior.value.search.SearchInvoice
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteGet
import ru.tchallenge.service.complex.convention.routing.RoutePost
import ru.tchallenge.service.complex.convention.routing.RoutePut

@CompileStatic
@RouterComponent("/workbooks")
class WorkbookRouter extends Router {

    @Autowired
    protected WorkbookFacade workbookFacade

    @RoutePost
    def create(@RequestBody WorkbookInvoice invoice) {
        return workbookFacade.create(invoice)
    }

    @RouteGet("/{id}")
    def get(@PathVariable("id") String id) {
        return workbookFacade.get(id)
    }

    @RouteGet
    def search(SearchInvoice<WorkbookInvoice> invoice) {
        return workbookFacade.search(invoice)
    }

    @RoutePut
    def update(@RequestBody WorkbookInvoice invoice) {
        return workbookFacade.update(invoice)
    }
}