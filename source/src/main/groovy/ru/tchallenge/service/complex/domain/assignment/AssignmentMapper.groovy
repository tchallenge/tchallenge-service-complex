package ru.tchallenge.service.complex.domain.assignment

import groovy.transform.CompileStatic

@CompileStatic
interface AssignmentMapper {

    Assignment asEntity(AssignmentInvoice invoice)

    Assignment asEntity(Assignment entity, AssignmentInvoice invoice)

    AssignmentInfo asInfo(Assignment entity)
}
