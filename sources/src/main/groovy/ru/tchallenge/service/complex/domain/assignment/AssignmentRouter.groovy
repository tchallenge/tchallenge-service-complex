package ru.tchallenge.service.complex.domain.assignment

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.common.GenericRouter
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RoutePatch

@CompileStatic
@RouterComponent("/assignments")
class AssignmentRouter extends GenericRouter {

    @Autowired
    protected AssignmentFacade assignmentFacade

    @RoutePatch
    def update(@RequestBody AssignmentInvoice invoice) {
        return assignmentFacade.update(invoice)
    }
}
