package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import ru.tchallenge.service.complex.reliability.correlation.CorrelationInfo

@CompileStatic
@Immutable(knownImmutableClasses = [ExceptionInfo])
class CorrelatedExceptionInfo {

    CorrelationInfo correlation
    ExceptionInfo exception
}
