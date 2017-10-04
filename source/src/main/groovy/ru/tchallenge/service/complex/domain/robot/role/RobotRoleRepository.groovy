package ru.tchallenge.service.complex.domain.robot.role

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository
import ru.tchallenge.service.complex.domain.shared.enumerated.EnumeratedAggregationSource

@CompileStatic
@EnumeratedAggregationSource('robot.role')
interface RobotRoleRepository extends GenericEnumeratedRepository<RobotRole> {

}
