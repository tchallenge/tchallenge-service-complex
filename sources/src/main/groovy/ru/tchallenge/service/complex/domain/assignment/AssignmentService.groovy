package ru.tchallenge.service.complex.domain.assignment

import groovy.transform.CompileStatic

@CompileStatic
interface AssignmentService {

    AssignmentInfo update(AssignmentInvoice invoice)
}
