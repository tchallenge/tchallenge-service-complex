package ru.tchallenge.service.complex.domain.shared.enumerated

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo

@CompileStatic
@Immutable
class EnumeratedAggregationInfo {

    Collection<EnumeratedInfo> items
    String type
}
