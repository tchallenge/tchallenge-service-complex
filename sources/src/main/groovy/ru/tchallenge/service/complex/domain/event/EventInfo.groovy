package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.behavior.value.GenericInfoValue
import ru.tchallenge.service.complex.convention.value.Value

@CompileStatic
@Value
class EventInfo extends GenericInfoValue {

    String textcode
}
