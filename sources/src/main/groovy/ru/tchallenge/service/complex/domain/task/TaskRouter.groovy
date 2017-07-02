package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.common.GenericRouter
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteGet
import ru.tchallenge.service.complex.convention.routing.RoutePost
import ru.tchallenge.service.complex.convention.routing.RoutePut

@CompileStatic
@RouterComponent("/tasks")
class TaskRouter extends GenericRouter {

    @RoutePost
    def create(@RequestBody TaskInvoice invoice) {
        return new TaskInfo(id: invoice.id)
    }

    @RouteGet("/{id}")
    def get(@PathVariable("id") String id) {
        return new TaskInfo(id: id)
    }

    @RouteGet
    def search(TaskInvoice invoice) {
        return [
                new TaskInfo(),
                new TaskInfo(),
                new TaskInfo()
        ]
    }

    @RoutePut
    def update(@RequestBody TaskInvoice invoice) {
        return new TaskInfo(id: invoice.id)
    }
}
