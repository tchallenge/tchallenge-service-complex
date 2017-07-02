package ru.tchallenge.service.complex.domain.assignment

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacade
import ru.tchallenge.service.complex.convention.component.FacadeComponent

@CompileStatic
@FacadeComponent
class AssignmentFacade extends GenericFacade {

    @Autowired
    protected AssignmentService assignmentService

    AssignmentInfo update(AssignmentInvoice invoice) {
        def account = authenticatedCandidate()
        return assignmentService.update(invoice)
    }
}
