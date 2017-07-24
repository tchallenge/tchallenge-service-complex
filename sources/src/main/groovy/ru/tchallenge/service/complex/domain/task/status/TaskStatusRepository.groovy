package ru.tchallenge.service.complex.domain.task.status

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository
import ru.tchallenge.service.complex.domain.shared.enumerated.EnumeratedAggregationSource

@CompileStatic
@EnumeratedAggregationSource('task.status')
interface TaskStatusRepository extends GenericEnumeratedRepository<TaskStatus> {

}
