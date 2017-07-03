package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.common.GenericRouter
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteGet
import ru.tchallenge.service.complex.convention.routing.RoutePatch
import ru.tchallenge.service.complex.convention.routing.RoutePost

@CompileStatic
@RouterComponent("/tasks")
class TaskRouter extends GenericRouter {

    @Autowired
    protected TaskFacade taskFacade

    @RoutePost
    def create(@RequestBody TaskInvoice invoice) {
        return taskFacade.create(invoice)
    }

    @RouteGet("/categories")
    Collection<EnumeratedInfo> getAllCategories() {
        return taskFacade.allCategories
    }

    @RouteGet("/difficulties")
    Collection<EnumeratedInfo> getAllDifficulties() {
        return taskFacade.allDifficulties
    }

    @RouteGet("/expectations")
    Collection<EnumeratedInfo> getAllExpectations() {
        return taskFacade.allExpectations
    }

    @RouteGet("/snippets/styles")
    Collection<EnumeratedInfo> getAllSnippetStyles() {
        return taskFacade.allSnippetStyles
    }

    @RouteGet("/statuses")
    Collection<EnumeratedInfo> getAllStatuses() {
        return taskFacade.allStatuses
    }

    @RouteGet("/{id}")
    def get(@PathVariable("id") String id) {
        return taskFacade.get(id)
    }

    @RouteGet
    def search(TaskSearchInvoice invoice) {
        return taskFacade.search(invoice)
    }

    @RoutePatch
    def update(@RequestBody TaskInvoice invoice) {
        return taskFacade.update(invoice)
    }
}
