package ru.tchallenge.service.complex.domain.task.option

import groovy.transform.CompileStatic

@CompileStatic
interface TaskOptionMapper {

    TaskOption asEntity(TaskOptionInvoice invoice)

    TaskOptionInfo asInfo(TaskOption entity)
}
