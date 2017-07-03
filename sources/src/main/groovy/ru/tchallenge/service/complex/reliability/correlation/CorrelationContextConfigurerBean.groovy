package ru.tchallenge.service.complex.reliability.correlation

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericContext
import ru.tchallenge.service.complex.convention.component.ContextComponent

@CompileStatic
@ContextComponent
class CorrelationContextConfigurerBean extends GenericContext implements CorrelationContextConfigurer {

    CorrelationInfo correlation
}
