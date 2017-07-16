package ru.tchallenge.service.complex.security

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.utility.routing.RouteSignature

@CompileStatic
interface SecurityInterceptorConfigurer extends SecurityInterceptor {

    void setExclusions(Collection<RouteSignature> exclusions)
}
