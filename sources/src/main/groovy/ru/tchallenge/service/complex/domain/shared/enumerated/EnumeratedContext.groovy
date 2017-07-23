package ru.tchallenge.service.complex.domain.shared.enumerated

import groovy.transform.CompileStatic

@CompileStatic
interface EnumeratedContext {

    Map<String, EnumeratedAggregationInfo> getAggregations()
}
