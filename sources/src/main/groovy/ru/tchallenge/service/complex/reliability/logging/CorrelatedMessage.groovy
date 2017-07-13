package ru.tchallenge.service.complex.reliability.logging

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import ru.tchallenge.service.complex.reliability.correlation.CorrelationInfo

@CompileStatic
@Immutable(knownImmutableClasses = [Object])
final class CorrelatedMessage {

    CorrelationInfo correlation
    Object message
}
