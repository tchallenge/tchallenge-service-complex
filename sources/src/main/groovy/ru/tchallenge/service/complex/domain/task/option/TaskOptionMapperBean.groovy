package ru.tchallenge.service.complex.domain.task.option

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent

@CompileStatic
@PackageScope
@MapperComponent
class TaskOptionMapperBean extends GenericMapperBean implements TaskOptionMapper {

    @Override
    TaskOption asEntity(TaskOptionInvoice invoice) {
        new TaskOption(
                content: invoice.content,
                correct: flag(invoice.correct),
                textcode: invoice.textcode
        )
    }

    @Override
    TaskOptionInfo asInfo(TaskOption entity) {
        new TaskOptionInfo(
                content: entity.content,
                correct: flag(entity.correct),
                textcode: entity.textcode
        )
    }
}
