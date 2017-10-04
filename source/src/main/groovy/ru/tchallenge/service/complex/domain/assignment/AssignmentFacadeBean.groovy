package ru.tchallenge.service.complex.domain.assignment

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacadeBean
import ru.tchallenge.service.complex.convention.component.FacadeComponent

@CompileStatic
@PackageScope
@FacadeComponent
class AssignmentFacadeBean extends GenericFacadeBean implements AssignmentFacade {

    @Autowired
    AssignmentService assignmentService

    AssignmentInfo update(AssignmentInvoice invoice) {
        def $account = authenticatedCandidate()
        // TODO: implement Assignment ownership validation
        assignmentService.update(invoice)
    }
}
