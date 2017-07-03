package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.common.GenericRouter
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteGet
import ru.tchallenge.service.complex.convention.routing.RoutePost
import ru.tchallenge.service.complex.convention.routing.RoutePut

@CompileStatic
@RouterComponent("/workbooks")
class WorkbookRouter extends GenericRouter {

    @Autowired
    protected WorkbookFacade workbookFacade

    @RoutePost
    def create(@RequestBody WorkbookInvoice invoice) {
        return workbookFacade.create(invoice)
    }

    @RouteGet("/statuses")
    def getAllStatuses() {
        return workbookFacade.allStatuses
    }

    @RouteGet("/{id}")
    def getById(@PathVariable("id") String id) {
        return workbookFacade.getById(id)
    }

    @RouteGet
    def search(WorkbookSearchInvoice invoice) {
        return workbookFacade.search(invoice)
    }

    @RoutePut
    def update(@RequestBody WorkbookInvoice invoice) {
        return workbookFacade.update(invoice)
    }
}
