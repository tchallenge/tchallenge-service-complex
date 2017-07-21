package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.common.GenericRouterBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteGet
import ru.tchallenge.service.complex.convention.routing.RoutePost
import ru.tchallenge.service.complex.convention.routing.RoutePut

@CompileStatic
@PackageScope
@RouterComponent('/workbooks')
class WorkbookRouterBean extends GenericRouterBean {

    @Autowired
    WorkbookFacade workbookFacade

    @RoutePost
    WorkbookInfo create(@RequestBody WorkbookInvoice invoice) {
        workbookFacade.create(invoice)
    }

    @RouteGet('/statuses')
    Collection<EnumeratedInfo> getAllStatuses() {
        workbookFacade.allStatuses
    }

    @RouteGet('/{id}')
    WorkbookInfo getById(@PathVariable('id') String id) {
        workbookFacade.getById(id)
    }

    @RouteGet
    SearchInfo<WorkbookInfo> search(WorkbookSearchInvoice invoice) {
        workbookFacade.search(invoice)
    }

    @RoutePut
    WorkbookInfo update(@RequestBody WorkbookInvoice invoice) {
        workbookFacade.update(invoice)
    }
}
