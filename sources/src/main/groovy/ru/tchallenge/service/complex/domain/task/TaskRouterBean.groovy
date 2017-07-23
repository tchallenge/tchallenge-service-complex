package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.common.GenericRouterBean
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteGet
import ru.tchallenge.service.complex.convention.routing.RoutePatch
import ru.tchallenge.service.complex.convention.routing.RoutePost

@CompileStatic
@RouterComponent('/tasks')
class TaskRouterBean extends GenericRouterBean {

    @Autowired
    TaskFacade taskFacade

    @RoutePost
    TaskInfo create(@RequestBody TaskInvoice invoice) {
        taskFacade.create(invoice)
    }

    @RouteGet('/{id}')
    TaskInfo getById(@PathVariable('id') String id) {
        taskFacade.getById(id)
    }

    @RouteGet
    SearchInfo<TaskInfo> search(TaskSearchInvoice invoice) {
        taskFacade.search(invoice)
    }

    @RoutePatch
    TaskInfo update(@RequestBody TaskInvoice invoice) {
        taskFacade.update(invoice)
    }
}
