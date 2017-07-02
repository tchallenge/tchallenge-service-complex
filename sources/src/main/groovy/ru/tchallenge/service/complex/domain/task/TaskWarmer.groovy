package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericWarmer
import ru.tchallenge.service.complex.convention.component.WarmerComponent

@CompileStatic
@WarmerComponent
class TaskWarmer extends GenericWarmer {

    @Autowired
    protected TaskService taskService

    @Override
    void run() {
        taskService.search(new TaskSearchInvoice(
                pageNumber: 1,
                pageSize: 1000
        ))
    }
}
