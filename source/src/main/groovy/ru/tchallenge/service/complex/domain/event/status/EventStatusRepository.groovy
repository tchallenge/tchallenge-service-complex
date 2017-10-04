package ru.tchallenge.service.complex.domain.event.status

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository
import ru.tchallenge.service.complex.domain.shared.enumerated.EnumeratedAggregationSource

@CompileStatic
@EnumeratedAggregationSource('event.status')
interface EventStatusRepository extends GenericEnumeratedRepository<EventStatus> {

}
