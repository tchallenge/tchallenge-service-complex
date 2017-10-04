package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

@CompileStatic
interface WorkbookMapper {

    Workbook asEntity(WorkbookInvoice invoice)

    Workbook asEntity(Workbook entity, WorkbookInvoice invoice)

    WorkbookInfo asInfo(Workbook entity)
}
