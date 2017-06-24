package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.behavior.component.Validator
import ru.tchallenge.service.complex.behavior.value.search.SearchInvoice
import ru.tchallenge.service.complex.convention.component.ValidatorComponent

@CompileStatic
@ValidatorComponent
class WorkbookValidator extends Validator {

    void ensureForCreation(WorkbookInvoice invoice) {
        // TODO: implement this method
    }

    void ensureForGet(String id) {
        // TODO: implement this method
    }

    void ensureForSearch(SearchInvoice<WorkbookInvoice> invoice) {
        // TODO: implement this method
    }

    void ensureForUpdate(WorkbookInvoice invoice) {
        // TODO: implement this method
    }
}
