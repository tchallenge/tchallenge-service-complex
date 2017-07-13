package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import ru.tchallenge.service.complex.reliability.correlation.CorrelationInfo

@CompileStatic
@Immutable
final class BaseExceptionInfo implements ExceptionInfo {

    String id
    ExceptionCategory category
    CorrelationInfo correlation
    String description
}
