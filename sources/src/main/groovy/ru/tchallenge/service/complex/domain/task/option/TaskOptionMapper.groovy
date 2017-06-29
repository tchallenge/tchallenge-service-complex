package ru.tchallenge.service.complex.domain.task.option

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericMapper
import ru.tchallenge.service.complex.convention.component.MapperComponent

@CompileStatic
@MapperComponent
class TaskOptionMapper extends GenericMapper {

    TaskOption asEntity(TaskOptionInvoice invoice) {
        return new TaskOption(
                content: invoice.content,
                correct: invoice.correct ? 1 : 0,
                textcode: invoice.textcode
        )
    }

    TaskOptionInfo asInfo(TaskOption entity) {
        return new TaskOptionInfo(
                content: entity.content,
                correct: entity.correct != 0,
                textcode: entity.textcode
        )
    }
}
