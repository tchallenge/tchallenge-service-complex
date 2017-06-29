package ru.tchallenge.service.complex.domain.task.input

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericMapper
import ru.tchallenge.service.complex.convention.component.MapperComponent

@CompileStatic
@MapperComponent
class TaskInputMapper extends GenericMapper {

    TaskInput asEntity(TaskInputInvoice invoice) {
        return new TaskInput(
                content: invoice.content,
                regex: invoice.regex ? 1 : 0,
                stance: invoice.stance
        )
    }

    TaskInputInfo asInfo(TaskInput entity) {
        return new TaskInputInfo(
                content: entity.content,
                regex: entity.regex != 0,
                stance: entity.stance
        )
    }
}
