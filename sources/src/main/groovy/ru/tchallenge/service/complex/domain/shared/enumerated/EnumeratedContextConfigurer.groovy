package ru.tchallenge.service.complex.domain.shared.enumerated

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericContextConfigurer

@CompileStatic
interface EnumeratedContextConfigurer extends EnumeratedContext, GenericContextConfigurer<Map<String, EnumeratedAggregationInfo>> {

}
