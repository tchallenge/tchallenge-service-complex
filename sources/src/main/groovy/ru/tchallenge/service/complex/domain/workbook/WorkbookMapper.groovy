package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericMapper
import ru.tchallenge.service.complex.convention.component.MapperComponent
import ru.tchallenge.service.complex.domain.account.AccountMapper
import ru.tchallenge.service.complex.domain.assignment.Assignment
import ru.tchallenge.service.complex.domain.assignment.AssignmentInfo
import ru.tchallenge.service.complex.domain.assignment.AssignmentMapper
import ru.tchallenge.service.complex.domain.event.EventMapper
import ru.tchallenge.service.complex.domain.maturity.MaturityRepository
import ru.tchallenge.service.complex.domain.specialization.SpecializationRepository
import ru.tchallenge.service.complex.domain.workbook.status.WorkbookStatusRepository
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.info
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.one
import static ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals.mapCollection

@CompileStatic
@MapperComponent
class WorkbookMapper extends GenericMapper {

    @Autowired
    protected AccountMapper accountMapper

    @Autowired
    protected AssignmentMapper assignmentMapper

    @Autowired
    protected EventMapper eventMapper

    @Autowired
    protected MaturityRepository maturityRepository

    @Autowired
    protected SpecializationRepository specializationRepository

    @Autowired
    protected WorkbookStatusRepository workbookStatusRepository

    Workbook asEntity(WorkbookInvoice invoice) {
        return asEntity(null, invoice)
    }

    Workbook asEntity(Workbook entity, WorkbookInvoice invoice) {
        entity = entity ?: new Workbook()
        return entity.with {
            id = invoice.id as Long ?: id
            event = invoice.event ? eventMapper.asEntity(event, invoice.event) : event
            it.owner = invoice.owner ? accountMapper.asEntity(it.owner, invoice.owner) : it.owner
            maturity = invoice.maturity ? one(maturityRepository, invoice.maturity) : maturity
            specialization = invoice.specialization ? one(specializationRepository, invoice.specialization) : specialization
            status = invoice.status ? one(workbookStatusRepository, invoice.status) : status
            it
        }
    }

    WorkbookInfo asInfo(Workbook entity) {
        return new WorkbookInfo(
                id: entity.id as String,
                event: eventMapper.asInfo(entity.event),
                owner: accountMapper.asInfo(entity.owner),
                scoreActual: entity.scoreActual,
                scoreMaximal: entity.scoreMaximal,
                assignments: mapAssignments(entity.assignments),
                maturity: info(entity.maturity),
                specialization: info(entity.specialization),
                status: info(entity.status)
        )
    }

    private Collection<AssignmentInfo> mapAssignments(Collection<Assignment> entities) {
        return mapCollection(entities) { Assignment it -> assignmentMapper.asInfo(it) }
    }
}
