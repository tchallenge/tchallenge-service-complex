package ru.tchallenge.service.complex.domain.assignment

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.domain.task.TaskInfo

@CompileStatic
@Immutable
class AssignmentInfo {

    String id

    String input
    Integer scoreActual
    Integer scoreMaximal

    Integer stance
    TaskInfo task

    EnumeratedInfo status
}
