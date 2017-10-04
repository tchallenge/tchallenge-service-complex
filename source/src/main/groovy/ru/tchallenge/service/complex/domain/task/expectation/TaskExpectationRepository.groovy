package ru.tchallenge.service.complex.domain.task.expectation

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository
import ru.tchallenge.service.complex.domain.shared.enumerated.EnumeratedAggregationSource

@CompileStatic
@EnumeratedAggregationSource('task.expectation')
interface TaskExpectationRepository extends GenericEnumeratedRepository<TaskExpectation> {

}
