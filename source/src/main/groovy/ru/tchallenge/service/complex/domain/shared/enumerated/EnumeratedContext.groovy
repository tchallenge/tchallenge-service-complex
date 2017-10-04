package ru.tchallenge.service.complex.domain.shared.enumerated

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericContext

@CompileStatic
interface EnumeratedContext extends GenericContext<Map<String, EnumeratedAggregationInfo>> {

}
