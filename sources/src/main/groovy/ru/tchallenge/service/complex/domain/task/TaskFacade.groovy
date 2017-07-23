package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.search.SearchInfo

@CompileStatic
interface TaskFacade {

    TaskInfo create(TaskInvoice invoice)

    TaskInfo getById(String id)

    SearchInfo<TaskInfo> search(TaskSearchInvoice invoice)

    TaskInfo update(TaskInvoice invoice)
}
