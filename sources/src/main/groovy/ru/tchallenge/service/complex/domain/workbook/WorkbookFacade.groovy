package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.search.SearchInfo

@CompileStatic
interface WorkbookFacade {

    WorkbookInfo create(WorkbookInvoice invoice)

    Collection<EnumeratedInfo> getAllStatuses()

    WorkbookInfo getById(String id)

    SearchInfo<WorkbookInfo> search(WorkbookSearchInvoice invoice)

    WorkbookInfo update(WorkbookInvoice invoice)
}
