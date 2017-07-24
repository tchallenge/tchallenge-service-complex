package ru.tchallenge.service.complex.reliability.violation

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import com.fasterxml.jackson.annotation.JsonIgnore

@CompileStatic
@Immutable(knownImmutableClasses = [Object])
class ResourceViolationInfo implements ViolationInfo {

    static ResourceViolationInfo notFound(String resource, String identifierName, Object identifierValue) {
        new ResourceViolationInfo(
                base: new BaseViolationInfo(
                        category: ViolationCategory.RESOURCE,
                        description: "Referenced ${resource} was not found",
                        textcode: 'X.RESOURCE.UNKNOWN'
                ),
                resource: resource,
                identifierName: identifierName,
                identifierValue: identifierValue
        )
    }

    @Delegate
    @JsonIgnore
    BaseViolationInfo base

    String identifierName
    String identifierValue
    String resource
}
