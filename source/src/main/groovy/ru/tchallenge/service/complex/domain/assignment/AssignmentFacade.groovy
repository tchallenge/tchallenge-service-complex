package ru.tchallenge.service.complex.domain.assignment

import groovy.transform.CompileStatic

@CompileStatic
interface AssignmentFacade {

    AssignmentInfo update(AssignmentInvoice invoice)
}
