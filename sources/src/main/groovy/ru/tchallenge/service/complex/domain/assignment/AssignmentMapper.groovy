package ru.tchallenge.service.complex.domain.assignment

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent
import ru.tchallenge.service.complex.domain.task.TaskMapper
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.info

@CompileStatic
@MapperComponent
class AssignmentMapper extends GenericMapperBean {

    @Autowired
    protected TaskMapper taskMapper

    Assignment asEntity(AssignmentInvoice invoice) {
        return asEntity(null, invoice)
    }

    Assignment asEntity(Assignment entity, AssignmentInvoice invoice) {
        entity = entity ?: new Assignment()
        return entity.with {
            id = invoice.id as Long ?: id
            input = invoice.input ?: input
            it
        }
    }

    AssignmentInfo asInfo(Assignment entity) {
        return new AssignmentInfo(
                id: entity.id as String,
                input: entity.input,
                scoreActual: entity.scoreActual,
                scoreMaximal: entity.scoreMaximal,
                stance: entity.stance,
                task: taskMapper.asInfo(entity.task),
                status: info(entity.status)
        )
    }
}
