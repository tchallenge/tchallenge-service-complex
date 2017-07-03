package ru.tchallenge.service.complex.reliability.correlation

import groovy.transform.CompileStatic

@CompileStatic
interface CorrelationContextConfigurer extends CorrelationContext {

    void setCorrelation(CorrelationInfo correlation)
}
