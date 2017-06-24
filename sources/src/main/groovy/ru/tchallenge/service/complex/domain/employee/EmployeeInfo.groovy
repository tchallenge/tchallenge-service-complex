package ru.tchallenge.service.complex.domain.employee

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInfoValue
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo

@CompileStatic
class EmployeeInfo extends GenericInfoValue {

    Collection<EnumeratedInfo> roles
}
