package ru.tchallenge.service.complex.domain.employee

import groovy.transform.TypeChecked

@TypeChecked
interface EmployeeMapper {

    EmployeeInfo asInfo(Employee entity)
}
