package ru.tchallenge.service.complex.reliability.correlation

import groovy.transform.CompileStatic
import groovy.transform.Immutable

@CompileStatic
@Immutable
final class CorrelationRequestClientInfo {

    String address
    String hostname
    Integer port
}
