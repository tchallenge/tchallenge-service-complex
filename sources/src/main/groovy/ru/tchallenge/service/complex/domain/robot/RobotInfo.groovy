package ru.tchallenge.service.complex.domain.robot

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo

@CompileStatic
@Immutable
class RobotInfo {

    Collection<EnumeratedInfo> roles
}
