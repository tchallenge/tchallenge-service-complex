package ru.tchallenge.service.complex.reliability.correlation

import javax.servlet.http.HttpServletRequest

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericInterceptorBean
import ru.tchallenge.service.complex.convention.component.InterceptorComponent

@CompileStatic
@InterceptorComponent
class CorrelationInterceptorBean extends GenericInterceptorBean implements CorrelationInterceptor {

    @Autowired
    protected CorrelationContextConfigurer correlationContextConfigurer

    @Override
    protected void preHandle(HttpServletRequest request) {
        def correlation = CorrelationInfo.of(request)
        correlationContextConfigurer.setValue(correlation)
        logAsInfo("Request correlation is created", correlation)
    }
}
