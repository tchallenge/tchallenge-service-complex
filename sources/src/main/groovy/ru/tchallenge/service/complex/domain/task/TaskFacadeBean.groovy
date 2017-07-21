package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacadeBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.FacadeComponent

@CompileStatic
@PackageScope
@FacadeComponent
class TaskFacadeBean extends GenericFacadeBean implements TaskFacade {

    @Autowired
    TaskService taskService

    @Override
    TaskInfo create(TaskInvoice invoice) {
        if (!isAuthenticatedTaskmod()) {
            throw unauthorized()
        }
        taskService.create(invoice)
    }

    @Override
    Collection<EnumeratedInfo> getAllCategories() {
        taskService.allCategories
    }

    @Override
    Collection<EnumeratedInfo> getAllDifficulties() {
        taskService.allDifficulties
    }

    @Override
    Collection<EnumeratedInfo> getAllExpectations() {
        taskService.allExpectations
    }

    @Override
    Collection<EnumeratedInfo> getAllSnippetStyles() {
        taskService.allSnippetStyles
    }

    @Override
    Collection<EnumeratedInfo> getAllStatuses() {
        taskService.allStatuses
    }

    @Override
    TaskInfo getById(String id) {
        if (!isAuthenticatedTaskview()) {
            throw unauthorized()
        }
        taskService.getById(id)
    }

    @Override
    SearchInfo<TaskInfo> search(TaskSearchInvoice invoice) {
        if (!isAuthenticatedTaskview()) {
            throw unauthorized()
        }
        taskService.search(invoice)
    }

    @Override
    TaskInfo update(TaskInvoice invoice) {
        if (!isAuthenticatedTaskmod()) {
            throw unauthorized()
        }
        taskService.update(invoice)
    }

    private boolean isAuthenticatedTaskmod() {
        authenticatedEmployee('TASKMOD')
    }

    private boolean isAuthenticatedTaskview() {
        authenticatedEmployee('TASKVIEW') || isAuthenticatedTaskmod()
    }
}
