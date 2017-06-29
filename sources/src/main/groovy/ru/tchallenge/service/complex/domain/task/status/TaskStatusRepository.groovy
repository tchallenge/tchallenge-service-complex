package ru.tchallenge.service.complex.domain.task.status

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository

@CompileStatic
interface TaskStatusRepository extends GenericEnumeratedRepository<TaskStatus> {

}
