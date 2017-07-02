package ru.tchallenge.service.complex.domain.task.input

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericMapper
import ru.tchallenge.service.complex.convention.component.MapperComponent
import static ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals.flag

@CompileStatic
@MapperComponent
class TaskInputMapper extends GenericMapper {

    TaskInput asEntity(TaskInputInvoice invoice) {
        return new TaskInput(
                content: invoice.content,
                regex: flag(invoice.regex),
                stance: invoice.stance
        )
    }

    TaskInputInfo asInfo(TaskInput entity) {
        return new TaskInputInfo(
                content: entity.content,
                regex: flag(entity.regex),
                stance: entity.stance
        )
    }
}