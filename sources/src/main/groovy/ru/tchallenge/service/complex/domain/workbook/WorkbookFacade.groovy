package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacadeBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.FacadeComponent

@CompileStatic
@FacadeComponent
class WorkbookFacade extends GenericFacadeBean {

    @Autowired
    protected WorkbookService workbookService

    @Autowired
    protected WorkbookValidator workbookValidator

    WorkbookInfo create(WorkbookInvoice invoice) {
        workbookValidator.ensureForCreation(invoice)
        return workbookService.create(invoice)
    }

    Collection<EnumeratedInfo> getAllStatuses() {
        return workbookService.allStatuses
    }

    WorkbookInfo getById(String id) {
        workbookValidator.ensureForGet(id)
        return workbookService.getById(id)
    }

    SearchInfo<WorkbookInfo> search(WorkbookSearchInvoice invoice) {
        workbookValidator.ensureForSearch(invoice)
        return workbookService.search(invoice)
    }

    WorkbookInfo update(WorkbookInvoice invoice) {
        workbookValidator.ensureForUpdate(invoice)
        return workbookService.update(invoice)
    }
}
