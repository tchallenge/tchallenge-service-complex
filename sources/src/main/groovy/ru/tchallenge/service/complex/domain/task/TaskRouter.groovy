package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.behavior.component.Router
import ru.tchallenge.service.complex.behavior.value.search.SearchInvoice
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteGet
import ru.tchallenge.service.complex.convention.routing.RoutePost
import ru.tchallenge.service.complex.convention.routing.RoutePut

@CompileStatic
@RouterComponent("/tasks")
class TaskRouter implements Router {

    @RoutePost
    def create(@RequestBody TaskInvoice invoice) {
        return new TaskInfoComplete(id: invoice.id)
    }

    @RouteGet("/{id}")
    def get(@PathVariable("id") String id) {
        return new TaskInfoComplete(id: id)
    }

    @RouteGet
    def search(SearchInvoice<TaskInvoice> invoice) {
        return [
                new TaskInfoComplete(),
                new TaskInfoComplete(),
                new TaskInfoComplete()
        ]
    }

    @RoutePut
    def update(@RequestBody TaskInvoice invoice) {
        return new TaskInfoComplete(id: invoice.id)
    }
}
