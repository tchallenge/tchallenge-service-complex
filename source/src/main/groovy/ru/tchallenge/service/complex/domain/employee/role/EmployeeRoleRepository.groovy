package ru.tchallenge.service.complex.domain.employee.role

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository
import ru.tchallenge.service.complex.domain.shared.enumerated.EnumeratedAggregationSource

@CompileStatic
@EnumeratedAggregationSource('employee.role')
interface EmployeeRoleRepository extends GenericEnumeratedRepository<EmployeeRole> {

}
