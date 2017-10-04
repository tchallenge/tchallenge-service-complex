package ru.tchallenge.service.complex.reliability.violation

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import com.fasterxml.jackson.annotation.JsonIgnore

import ru.tchallenge.service.complex.reliability.violation.constraints.BaseViolationConstraintInfo
import ru.tchallenge.service.complex.reliability.violation.constraints.ViolationConstraintInfo

@CompileStatic
@Immutable(knownImmutableClasses = [ViolationConstraintInfo])
class ContractViolationInfo implements ViolationInfo {

    static ContractViolationInfo missing(String path) {
        new ContractViolationInfo(
                base: new BaseViolationInfo(
                        category: ViolationCategory.CONTRACT,
                        description: "Property ${path} is missing",
                        textcode: 'X.CONTRACT.PROPERTY.MISSING'
                ),
                constraint: BaseViolationConstraintInfo.required(),
                path: path,
                value: null
        )
    }

    @Delegate
    @JsonIgnore
    BaseViolationInfo base

    ViolationConstraintInfo constraint
    String path
    String value
}
