package ru.tchallenge.service.complex.domain.assignment

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@PackageScope
@ServiceComponent
class AssignmentServiceBean extends GenericServiceBean implements AssignmentService {

    @Autowired
    AssignmentMapper assignmentMapper

    @Autowired
    AssignmentRepository assignmentRepository

    @Override
    AssignmentInfo update(AssignmentInvoice invoice) {
        def $entity = assignmentById(invoice.id)
        def $trimmedInvoice = invoice.with {
            id = null
            it
        }
        def $mergedEntity = assignmentMapper.asEntity($entity, $trimmedInvoice)
        return saveAndInfo($mergedEntity)
    }

    private Assignment assignmentById(String id) {
        def $result = assignmentRepository.findById(id)
        if (!$result) {
            throw unknownAssignment()
        }
        $result
    }

    private AssignmentInfo info(Assignment entity) {
        assignmentMapper.asInfo(entity)
    }

    private Assignment save(Assignment entity) {
        assignmentRepository.save(entity)
    }

    private AssignmentInfo saveAndInfo(Assignment entity) {
        info(save(entity))
    }

    private static RuntimeException unknownAssignment() {
        new RuntimeException('referenced assignment does not exist')
    }
}
