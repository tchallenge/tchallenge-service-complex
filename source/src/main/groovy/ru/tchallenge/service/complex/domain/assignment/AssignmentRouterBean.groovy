package ru.tchallenge.service.complex.domain.assignment

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.common.GenericRouterBean
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RoutePatch

@CompileStatic
@RouterComponent('/assignments')
class AssignmentRouterBean extends GenericRouterBean {

    @Autowired
    AssignmentFacade assignmentFacade

    @RoutePatch
    AssignmentInfo update(@RequestBody AssignmentInvoice invoice) {
        assignmentFacade.update(invoice)
    }
}
