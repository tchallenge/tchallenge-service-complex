package ru.tchallenge.service.complex

import groovy.transform.CompileStatic

import javax.annotation.PostConstruct

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RequestMethod

import ru.tchallenge.service.complex.security.SecurityInterceptorConfigurer
import ru.tchallenge.service.complex.utility.routing.RouteSignature

@CompileStatic
@Configuration
class SecurityConfigurator {

    @Autowired
    SecurityInterceptorConfigurer securityInterceptorConfigurer

    @PostConstruct
    protected void init() {
        // TODO: collect exclusions based on NoAuthentication and RouteMethod annotations
        securityInterceptorConfigurer.exclusions = [
                new RouteSignature(
                        method: RequestMethod.POST,
                        uri: '/security/rescues'
                ),
                new RouteSignature(
                        method: RequestMethod.POST,
                        uri: '/security/tokens'
                ),
                new RouteSignature(
                        method: RequestMethod.POST,
                        uri: '/accounts/claims'
                ),
                new RouteSignature(
                        method: RequestMethod.GET,
                        pattern: true,
                        uri: '/events/.+'
                )
        ]
    }
}
