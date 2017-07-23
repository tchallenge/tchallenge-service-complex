package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericWarmerBean
import ru.tchallenge.service.complex.convention.component.WarmerComponent

@CompileStatic
@PackageScope
@WarmerComponent
class TaskWarmerBean extends GenericWarmerBean implements TaskWarmer {

    private static final Integer PAGE_COUNT = 10
    private static final Integer PAGE_SIZE = 10

    @Autowired
    TaskService taskService

    @Override
    void run() {
        taskService.allCategories
        taskService.allDifficulties
        taskService.allExpectations
        taskService.allSnippetStyles
        taskService.allStatuses
        PAGE_COUNT.times { int page -> searchByPage(page + 1) }
    }

    private void searchByPage(int page) {
        taskService.search(new TaskSearchInvoice(
                pageNumber: page,
                pageSize: PAGE_SIZE
        ))
    }
}
