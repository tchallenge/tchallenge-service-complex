package ru.tchallenge.service.complex.domain.maturity

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository
import ru.tchallenge.service.complex.domain.shared.enumerated.EnumeratedAggregationSource

@CompileStatic
@EnumeratedAggregationSource('maturity')
interface MaturityRepository extends GenericEnumeratedRepository<Maturity> {

}
