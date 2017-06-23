package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.behavior.component.Facade
import ru.tchallenge.service.complex.behavior.value.search.SearchInvoice
import ru.tchallenge.service.complex.convention.component.FacadeComponent

@CompileStatic
@FacadeComponent
class WorkbookFacade extends Facade {

    def create(WorkbookInvoice invoice) {
        return new WorkbookInfoComplete(id: invoice.id)
    }

    def get(String id) {
        return new WorkbookInfoComplete(id: id)
    }

    def search(SearchInvoice<WorkbookInvoice> invoice) {
        return [
                new WorkbookInfoComplete(),
                new WorkbookInfoComplete(),
                new WorkbookInfoComplete()
        ]
    }

    def update(WorkbookInvoice invoice) {
        return new WorkbookInfoComplete(id: invoice.id)
    }
}
