package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.search.SearchInfo

@CompileStatic
interface TaskService {

    TaskInfo create(TaskInvoice invoice)

    Collection<EnumeratedInfo> getAllCategories()

    Collection<EnumeratedInfo> getAllDifficulties()

    Collection<EnumeratedInfo> getAllExpectations()

    Collection<EnumeratedInfo> getAllSnippetStyles()

    Collection<EnumeratedInfo> getAllStatuses()

    TaskInfo getById(String id)

    SearchInfo<TaskInfo> search(TaskSearchInvoice invoice)

    TaskInfo update(TaskInvoice invoice)
}
