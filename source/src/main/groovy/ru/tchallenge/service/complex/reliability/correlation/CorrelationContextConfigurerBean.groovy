package ru.tchallenge.service.complex.reliability.correlation

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.GenericContextConfigurerBean
import ru.tchallenge.service.complex.convention.component.RequestContextComponent

@CompileStatic
@PackageScope
@RequestContextComponent
class CorrelationContextConfigurerBean extends GenericContextConfigurerBean<CorrelationInfo> implements CorrelationContextConfigurer {

}
