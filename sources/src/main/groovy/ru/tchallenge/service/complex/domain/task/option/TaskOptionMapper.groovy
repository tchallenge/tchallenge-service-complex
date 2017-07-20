package ru.tchallenge.service.complex.domain.task.option

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent

@CompileStatic
@MapperComponent
class TaskOptionMapper extends GenericMapperBean {

    TaskOption asEntity(TaskOptionInvoice invoice) {
        return new TaskOption(
                content: invoice.content,
                correct: flag(invoice.correct),
                textcode: invoice.textcode
        )
    }

    TaskOptionInfo asInfo(TaskOption entity) {
        return new TaskOptionInfo(
                content: entity.content,
                correct: flag(entity.correct),
                textcode: entity.textcode
        )
    }
}
