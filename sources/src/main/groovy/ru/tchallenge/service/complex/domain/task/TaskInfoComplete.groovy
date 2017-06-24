package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.behavior.value.GenericInfoValue
import ru.tchallenge.service.complex.convention.value.Value

@CompileStatic
@Value
class TaskInfoComplete extends GenericInfoValue {

    String id
}
