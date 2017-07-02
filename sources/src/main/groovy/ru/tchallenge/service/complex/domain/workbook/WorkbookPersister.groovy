package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalPersister
import ru.tchallenge.service.complex.convention.component.PersisterComponent
import ru.tchallenge.service.complex.domain.assignment.Assignment

@CompileStatic
@PersisterComponent
class WorkbookPersister extends GenericOrdinalPersister<Workbook> {

    @Override
    protected void prepare(Workbook entity) {
        if (!entity.id) {
            entity.id = nextOrdinalSequence("domain.workbook")
        }
        if (entity.assignments) {
            entity.assignments.forEach { Assignment it -> it.workbook = entity }
        }
    }
}
