package ru.tchallenge.service.complex.domain.task.category

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository
import ru.tchallenge.service.complex.domain.shared.enumerated.EnumeratedAggregationSource

@CompileStatic
@EnumeratedAggregationSource('task.category')
interface TaskCategoryRepository extends GenericEnumeratedRepository<TaskCategory> {

}
