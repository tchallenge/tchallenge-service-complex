package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericMapper
import ru.tchallenge.service.complex.convention.component.MapperComponent

@CompileStatic
@MapperComponent
class WorkbookMapper extends GenericMapper {

    static WorkbookEntity asEntity(WorkbookInvoice invoice) {
        return new WorkbookEntity(
                id: invoice.id as Long
        )
    }

    static WorkbookInfo asInfoComplete(WorkbookEntity entity) {
        return new WorkbookInfo(
                id: entity.id as String
        )
    }
}
