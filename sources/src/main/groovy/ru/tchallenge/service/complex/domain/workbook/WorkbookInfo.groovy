package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.domain.account.AccountInfo
import ru.tchallenge.service.complex.domain.assignment.AssignmentInfo
import ru.tchallenge.service.complex.domain.event.EventInfo

@CompileStatic
@Immutable
class WorkbookInfo {

    String id
    Integer scoreActual
    Integer scoreMaximal
    Collection<AssignmentInfo> assignments
    EventInfo event
    AccountInfo owner
    EnumeratedInfo maturity
    EnumeratedInfo specialization
    EnumeratedInfo status
}
