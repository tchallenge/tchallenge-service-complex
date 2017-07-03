package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInfoValue
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.domain.account.AccountInfo
import ru.tchallenge.service.complex.domain.assignment.AssignmentInfo
import ru.tchallenge.service.complex.domain.event.EventInfo

@CompileStatic
class WorkbookInfo extends GenericInfoValue {

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
