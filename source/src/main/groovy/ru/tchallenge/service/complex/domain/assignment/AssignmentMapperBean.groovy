package ru.tchallenge.service.complex.domain.assignment

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent
import ru.tchallenge.service.complex.domain.task.TaskMapper

@CompileStatic
@PackageScope
@MapperComponent
class AssignmentMapperBean extends GenericMapperBean implements AssignmentMapper {

    @Autowired
    TaskMapper taskMapper

    @Override
    Assignment asEntity(AssignmentInvoice invoice) {
        asEntity(null, invoice)
    }

    @Override
    Assignment asEntity(Assignment entity, AssignmentInvoice invoice) {
        def $result = entity ?: new Assignment()
        $result.with {
            id = invoice.id as Long ?: id
            input = invoice.input ?: input
            it
        }
    }

    @Override
    AssignmentInfo asInfo(Assignment entity) {
        new AssignmentInfo(
                id: entity.id as String,
                input: entity.input,
                scoreActual: entity.scoreActual,
                scoreMaximal: entity.scoreMaximal,
                stance: entity.stance,
                task: taskMapper.asInfo(entity.task),
                status: enumerateds.info(entity.status)
        )
    }
}
