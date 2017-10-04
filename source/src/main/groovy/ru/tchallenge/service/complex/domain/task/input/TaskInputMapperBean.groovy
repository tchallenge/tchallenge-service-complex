package ru.tchallenge.service.complex.domain.task.input

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent

@CompileStatic
@PackageScope
@MapperComponent
class TaskInputMapperBean extends GenericMapperBean implements TaskInputMapper {

    @Override
    TaskInput asEntity(TaskInputInvoice invoice) {
        new TaskInput(
                content: invoice.content,
                regex: flag(invoice.regex),
                stance: invoice.stance
        )
    }

    @Override
    TaskInputInfo asInfo(TaskInput entity) {
        new TaskInputInfo(
                content: entity.content,
                regex: flag(entity.regex),
                stance: entity.stance
        )
    }
}
