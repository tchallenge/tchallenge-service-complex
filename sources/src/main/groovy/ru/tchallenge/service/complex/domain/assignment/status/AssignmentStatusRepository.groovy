package ru.tchallenge.service.complex.domain.assignment.status

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository
import ru.tchallenge.service.complex.domain.shared.enumerated.EnumeratedAggregationSource

@CompileStatic
@EnumeratedAggregationSource('assignment.status')
interface AssignmentStatusRepository extends GenericEnumeratedRepository<AssignmentStatus> {

}
