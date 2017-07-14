package ru.tchallenge.service.complex.reliability.correlation

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericContextBean
import ru.tchallenge.service.complex.convention.component.ContextComponent

@CompileStatic
@ContextComponent
class CorrelationContextConfigurerBean extends GenericContextBean implements CorrelationContextConfigurer {

    CorrelationInfo correlation
}
