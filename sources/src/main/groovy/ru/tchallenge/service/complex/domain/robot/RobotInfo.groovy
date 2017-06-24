package ru.tchallenge.service.complex.domain.robot

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInfoValue
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo

@CompileStatic
class RobotInfo extends GenericInfoValue {

    Collection<EnumeratedInfo> roles
}
