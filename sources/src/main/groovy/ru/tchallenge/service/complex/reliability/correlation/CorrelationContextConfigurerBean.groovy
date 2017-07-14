package ru.tchallenge.service.complex.reliability.correlation

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericContextBean
import ru.tchallenge.service.complex.convention.component.ContextComponent

@CompileStatic
@ContextComponent
class CorrelationContextConfigurerBean extends GenericContextBean implements CorrelationContextConfigurer {

    private volatile CorrelationInfo correlation

    @Override
    Optional<CorrelationInfo> getCorrelation() {
        return Optional.ofNullable(correlation)
    }

    @Override
    void setCorrelation(CorrelationInfo correlation) {
        this.correlation = correlation
    }
}
