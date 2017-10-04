package ru.tchallenge.service.complex.domain.sample.status

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository
import ru.tchallenge.service.complex.domain.shared.enumerated.EnumeratedAggregationSource

@CompileStatic
@EnumeratedAggregationSource('sample.status')
interface SampleStatusRepository extends GenericEnumeratedRepository<SampleStatus> {

}
