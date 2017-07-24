package ru.tchallenge.service.complex.domain.specialization

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository
import ru.tchallenge.service.complex.domain.shared.enumerated.EnumeratedAggregationSource

@CompileStatic
@EnumeratedAggregationSource('specialization')
interface SpecializationRepository extends GenericEnumeratedRepository<Specialization> {

}
