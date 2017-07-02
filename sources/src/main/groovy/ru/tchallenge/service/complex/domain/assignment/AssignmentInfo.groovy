package ru.tchallenge.service.complex.domain.assignment

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInfoValue
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.domain.task.TaskInfo

@CompileStatic
class AssignmentInfo extends GenericInfoValue {

    String id

    String input
    Integer scoreActual
    Integer scoreMaximal

    Integer stance
    TaskInfo task

    EnumeratedInfo status
}
