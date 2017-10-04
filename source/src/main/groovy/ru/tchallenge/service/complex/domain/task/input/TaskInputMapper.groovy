package ru.tchallenge.service.complex.domain.task.input

import groovy.transform.CompileStatic

@CompileStatic
interface TaskInputMapper {

    TaskInput asEntity(TaskInputInvoice invoice)

    TaskInputInfo asInfo(TaskInput entity)
}
