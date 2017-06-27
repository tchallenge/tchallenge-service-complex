package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericValidator
import ru.tchallenge.service.complex.common.search.GenericSearchInvoice
import ru.tchallenge.service.complex.convention.component.ValidatorComponent

@CompileStatic
@ValidatorComponent
class WorkbookValidator extends GenericValidator {

    void ensureForCreation(WorkbookInvoice invoice) {
        // TODO: implement this method
    }

    void ensureForGet(String id) {
        // TODO: implement this method
    }

    void ensureForSearch(WorkbookInvoice invoice) {
        // TODO: implement this method
    }

    void ensureForUpdate(WorkbookInvoice invoice) {
        // TODO: implement this method
    }
}
