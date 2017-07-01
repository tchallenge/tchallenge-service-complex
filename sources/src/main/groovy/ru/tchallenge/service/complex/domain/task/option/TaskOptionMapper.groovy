package ru.tchallenge.service.complex.domain.task.option

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericMapper
import ru.tchallenge.service.complex.convention.component.MapperComponent
import static ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals.flag

@CompileStatic
@MapperComponent
class TaskOptionMapper extends GenericMapper {

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
