package ru.tchallenge.service.complex.reliability.correlation

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericContextConfigurer

@CompileStatic
interface CorrelationContextConfigurer extends CorrelationContext, GenericContextConfigurer<CorrelationInfo> {

}
