package ru.tchallenge.service.complex.security

import javax.servlet.http.HttpServletRequest

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInterceptor
import ru.tchallenge.service.complex.convention.component.InterceptorComponent

@CompileStatic
@InterceptorComponent
class SecurityInterceptor extends GenericInterceptor {

    @Override
    boolean preHandle(HttpServletRequest request) {
        return true
    }
}
