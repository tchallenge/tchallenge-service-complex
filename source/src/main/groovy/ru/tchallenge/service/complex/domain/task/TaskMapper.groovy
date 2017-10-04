package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic

@CompileStatic
interface TaskMapper {

    Task asEntity(TaskInvoice invoice)

    Task asEntity(Task entity, TaskInvoice invoice)

    TaskInfo asInfo(Task entity)
}
