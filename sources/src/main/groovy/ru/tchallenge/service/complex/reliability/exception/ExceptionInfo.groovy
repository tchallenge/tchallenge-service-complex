package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.reliability.correlation.CorrelationInfo

@CompileStatic
interface ExceptionInfo {

    String getId()

    ExceptionCategory getCategory()

    CorrelationInfo getCorrelation()

    String getDescription()
}
