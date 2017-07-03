package ru.tchallenge.service.complex.reliability.correlation

import groovy.transform.CompileStatic
import groovy.transform.Immutable

@CompileStatic
@Immutable
class CorrelationInfo {

    String id
    CorrelationRequestInfo request
}
