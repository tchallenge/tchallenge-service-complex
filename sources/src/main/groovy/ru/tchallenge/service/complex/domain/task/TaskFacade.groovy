package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacade
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.FacadeComponent

@CompileStatic
@FacadeComponent
class TaskFacade extends GenericFacade {

    @Autowired
    protected TaskService taskService

    TaskInfo create(TaskInvoice invoice) {
        if (!isAuthenticatedTaskmod()) {
            throw unauthorized()
        }
        return taskService.create(invoice)
    }

    TaskInfo get(String id) {
        if (!isAuthenticatedTaskview()) {
            throw unauthorized()
        }
        return taskService.get(id)
    }

    SearchInfo<TaskInfo> search(TaskSearchInvoice invoice) {
        if (!isAuthenticatedTaskview()) {
            throw unauthorized()
        }
        return taskService.search(invoice)
    }

    TaskInfo update(TaskInvoice invoice) {
        if (!isAuthenticatedTaskmod()) {
            throw unauthorized()
        }
        return taskService.update(invoice)
    }

    private boolean isAuthenticatedTaskmod() {
        return authenticatedEmployee("TASKMOD")
    }

    private boolean isAuthenticatedTaskview() {
        return authenticatedEmployee("TASKVIEW") || isAuthenticatedTaskmod()
    }
}
