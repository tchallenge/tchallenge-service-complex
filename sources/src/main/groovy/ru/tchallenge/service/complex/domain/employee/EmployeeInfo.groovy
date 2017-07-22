package ru.tchallenge.service.complex.domain.employee

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo

@CompileStatic
@Immutable
class EmployeeInfo {

    Collection<EnumeratedInfo> roles
}
