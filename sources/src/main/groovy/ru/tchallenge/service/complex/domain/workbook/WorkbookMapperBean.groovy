package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent
import ru.tchallenge.service.complex.domain.account.AccountMapper
import ru.tchallenge.service.complex.domain.assignment.Assignment
import ru.tchallenge.service.complex.domain.assignment.AssignmentInfo
import ru.tchallenge.service.complex.domain.assignment.AssignmentMapper
import ru.tchallenge.service.complex.domain.event.EventMapper
import ru.tchallenge.service.complex.domain.maturity.MaturityRepository
import ru.tchallenge.service.complex.domain.specialization.SpecializationRepository
import ru.tchallenge.service.complex.domain.workbook.status.WorkbookStatusRepository

@CompileStatic
@PackageScope
@MapperComponent
class WorkbookMapperBean extends GenericMapperBean implements WorkbookMapper {

    @Autowired
    AccountMapper accountMapper

    @Autowired
    AssignmentMapper assignmentMapper

    @Autowired
    EventMapper eventMapper

    @Autowired
    MaturityRepository maturityRepository

    @Autowired
    SpecializationRepository specializationRepository

    @Autowired
    WorkbookStatusRepository workbookStatusRepository

    @Override
    Workbook asEntity(WorkbookInvoice invoice) {
        asEntity(null, invoice)
    }

    @Override
    Workbook asEntity(Workbook entity, WorkbookInvoice invoice) {
        def $result = entity ?: new Workbook()
        $result.with {
            id = invoice.id as Long ?: id
            event = invoice.event ? eventMapper.asEntity(event, invoice.event) : event
            it.owner = invoice.owner ? accountMapper.asEntity(it.owner, invoice.owner) : it.owner
            maturity = invoice.maturity ? enumerateds.one(maturityRepository, invoice.maturity) : maturity
            specialization = invoice.specialization ? enumerateds.one(specializationRepository, invoice.specialization) : specialization
            status = invoice.status ? enumerateds.one(workbookStatusRepository, invoice.status) : status
            it
        }
    }

    @Override
    WorkbookInfo asInfo(Workbook entity) {
        new WorkbookInfo(
                id: entity.id as String,
                event: eventMapper.asInfo(entity.event),
                owner: accountMapper.asInfo(entity.owner),
                scoreActual: entity.scoreActual,
                scoreMaximal: entity.scoreMaximal,
                assignments: mapAssignments(entity.assignments),
                maturity: enumerateds.info(entity.maturity),
                specialization: enumerateds.info(entity.specialization),
                status: enumerateds.info(entity.status)
        )
    }

    private Collection<AssignmentInfo> mapAssignments(Collection<Assignment> entities) {
        mapCollection(entities) { Assignment it -> assignmentMapper.asInfo(it) }
    }
}
