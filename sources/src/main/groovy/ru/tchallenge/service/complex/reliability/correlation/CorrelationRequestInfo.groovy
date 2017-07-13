package ru.tchallenge.service.complex.reliability.correlation

import java.time.Instant

import groovy.transform.CompileStatic
import groovy.transform.Immutable

@CompileStatic
@Immutable(knownImmutableClasses = [Instant])
final class CorrelationRequestInfo {

    CorrelationRequestClientInfo client
    String method
    String uri
    Instant receivedAt
}
