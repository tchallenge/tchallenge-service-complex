package ru.tchallenge.service.complex.domain.event.category

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository
import ru.tchallenge.service.complex.domain.shared.enumerated.EnumeratedAggregationSource

@CompileStatic
@EnumeratedAggregationSource('event.category')
interface EventCategoryRepository extends GenericEnumeratedRepository<EventCategory> {

}
