package ru.tchallenge.service.complex.reliability.correlation

import groovy.transform.CompileStatic

@CompileStatic
interface CorrelationContext {

    Optional<CorrelationInfo> getCorrelation()
}
