package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInfoValue
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.domain.event.interval.EventIntervalInfo

@CompileStatic
class EventInfo extends GenericInfoValue {

    String id
    String textcode
    String title
    String subtitle
    String description
    String greeting
    Collection<EventIntervalInfo> intervals
    EnumeratedInfo category
    EnumeratedInfo status
}
