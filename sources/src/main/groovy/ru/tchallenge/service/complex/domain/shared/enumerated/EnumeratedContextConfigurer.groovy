package ru.tchallenge.service.complex.domain.shared.enumerated

import groovy.transform.CompileStatic

@CompileStatic
interface EnumeratedContextConfigurer extends EnumeratedContext {

    void setAggregations(Map<String, EnumeratedAggregationInfo> aggregations)
}
