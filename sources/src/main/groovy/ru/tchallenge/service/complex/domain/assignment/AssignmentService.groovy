package ru.tchallenge.service.complex.domain.assignment

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@ServiceComponent
class AssignmentService extends GenericServiceBean {

    @Autowired
    protected AssignmentMapper assignmentMapper

    @Autowired
    protected AssignmentRepository assignmentRepository

    AssignmentInfo update(AssignmentInvoice invoice) {
        def entity = assignmentById(invoice.id)
        def trimmedInvoice = invoice.with {
            id = null
            it
        }
        def mergedEntity = assignmentMapper.asEntity(entity, trimmedInvoice)
        return saveAndInfo(mergedEntity)
    }

    private Assignment assignmentById(String id) {
        def result = assignmentRepository.findById(id)
        if (!result) {
            throw unknownAssignment()
        }
        return result
    }

    private AssignmentInfo info(Assignment entity) {
        return assignmentMapper.asInfo(entity)
    }

    private Assignment save(Assignment entity) {
        return assignmentRepository.save(entity)
    }

    private AssignmentInfo saveAndInfo(Assignment entity) {
        return info(save(entity))
    }

    private static RuntimeException unknownAssignment() {
        return new RuntimeException("referenced assignment does not exist")
    }
}
