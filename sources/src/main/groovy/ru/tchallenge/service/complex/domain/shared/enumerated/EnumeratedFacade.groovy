package ru.tchallenge.service.complex.domain.shared.enumerated

import groovy.transform.CompileStatic

@CompileStatic
interface EnumeratedFacade {

    Collection<EnumeratedAggregationInfo> getAll()

    EnumeratedAggregationInfo getAggregationByType(String type)
}
