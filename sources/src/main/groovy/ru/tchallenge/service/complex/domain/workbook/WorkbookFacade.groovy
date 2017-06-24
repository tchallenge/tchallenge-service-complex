package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.behavior.component.GenericFacade
import ru.tchallenge.service.complex.behavior.value.EnumeratedInfo
import ru.tchallenge.service.complex.behavior.value.search.SearchInfo
import ru.tchallenge.service.complex.behavior.value.search.SearchInvoice
import ru.tchallenge.service.complex.convention.component.FacadeComponent

@CompileStatic
@FacadeComponent
class WorkbookFacade extends GenericFacade {

    @Autowired
    protected WorkbookService workbookService

    @Autowired
    protected WorkbookValidator workbookValidator

    WorkbookInfo create(WorkbookInvoice invoice) {
        workbookValidator.ensureForCreation(invoice)
        return workbookService.create(invoice)
    }

    WorkbookInfo get(String id) {
        workbookValidator.ensureForGet(id)
        return workbookService.get(id)
    }

    SearchInfo<WorkbookInfo> search(SearchInvoice<WorkbookInvoice> invoice) {
        workbookValidator.ensureForSearch(invoice)
        return workbookService.search(invoice)
    }

    WorkbookInfo update(WorkbookInvoice invoice) {
        workbookValidator.ensureForUpdate(invoice)
        return workbookService.update(invoice)
    }

    Collection<EnumeratedInfo> getStatuses() {
        return workbookService.statuses
    }
}
