package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericMapper
import ru.tchallenge.service.complex.convention.component.MapperComponent

@CompileStatic
@MapperComponent
class WorkbookMapper extends GenericMapper {

    static Workbook asEntity(WorkbookInvoice invoice) {
        return new Workbook(
                id: invoice.id as Long
        )
    }

    static WorkbookInfo asInfoComplete(Workbook entity) {
        return new WorkbookInfo(
                id: entity.id as String
        )
    }
}
