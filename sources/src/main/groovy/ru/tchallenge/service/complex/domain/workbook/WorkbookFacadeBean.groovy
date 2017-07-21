package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacadeBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.FacadeComponent

@CompileStatic
@PackageScope
@FacadeComponent
class WorkbookFacadeBean extends GenericFacadeBean implements WorkbookFacade {

    @Autowired
    WorkbookService workbookService

    @Override
    WorkbookInfo create(WorkbookInvoice invoice) {
        workbookService.create(invoice)
    }

    @Override
    Collection<EnumeratedInfo> getAllStatuses() {
        workbookService.allStatuses
    }

    @Override
    WorkbookInfo getById(String id) {
        workbookService.getById(id)
    }

    @Override
    SearchInfo<WorkbookInfo> search(WorkbookSearchInvoice invoice) {
        workbookService.search(invoice)
    }

    @Override
    WorkbookInfo update(WorkbookInvoice invoice) {
        workbookService.update(invoice)
    }
}
