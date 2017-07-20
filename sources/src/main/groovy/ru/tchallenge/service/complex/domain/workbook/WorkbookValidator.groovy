package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericValidatorBean
import ru.tchallenge.service.complex.convention.component.ValidatorComponent

@CompileStatic
@ValidatorComponent
class WorkbookValidator extends GenericValidatorBean {

    void ensureForCreation(WorkbookInvoice invoice) {
        // TODO: implement this method
    }

    void ensureForGet(String id) {
        // TODO: implement this method
    }

    void ensureForSearch(WorkbookSearchInvoice invoice) {
        // TODO: implement this method
    }

    void ensureForUpdate(WorkbookInvoice invoice) {
        // TODO: implement this method
    }
}
