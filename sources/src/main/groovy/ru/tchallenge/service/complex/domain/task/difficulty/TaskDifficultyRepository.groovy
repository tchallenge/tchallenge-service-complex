package ru.tchallenge.service.complex.domain.task.difficulty

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository
import ru.tchallenge.service.complex.domain.shared.enumerated.EnumeratedAggregationSource

@CompileStatic
@EnumeratedAggregationSource('task.difficulty')
interface TaskDifficultyRepository extends GenericEnumeratedRepository<TaskDifficulty> {

}
