package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.search.SearchInfo

@CompileStatic
interface WorkbookService {

    WorkbookInfo create(WorkbookInvoice invoice)

    WorkbookInfo getById(String id)

    SearchInfo<WorkbookInfo> search(WorkbookSearchInvoice invoice)

    WorkbookInfo update(WorkbookInvoice invoice)
}
