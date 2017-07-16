package ru.tchallenge.service.complex.security

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import javax.servlet.http.HttpServletRequest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RequestMethod

import ru.tchallenge.service.complex.common.GenericInterceptorBean
import ru.tchallenge.service.complex.convention.component.InterceptorComponent
import ru.tchallenge.service.complex.reliability.exception.SecurityViolationException
import ru.tchallenge.service.complex.security.authentication.AuthenticationContextConfigurer
import ru.tchallenge.service.complex.security.authentication.AuthenticationInfo
import ru.tchallenge.service.complex.utility.routing.RouteSignature

@CompileStatic
@PackageScope
@InterceptorComponent
class SecurityInterceptorConfigurerBean extends GenericInterceptorBean implements SecurityInterceptorConfigurer {

    @Autowired
    AuthenticationContextConfigurer authenticationContextConfigurer

    @Autowired
    SecurityService securityService

    @Value('${tchallenge.security.certificate.payload.header}')
    String certificatePayloadHeader

    @Value('${tchallenge.security.token.payload.header}')
    String tokenPayloadHeader

    Collection<RouteSignature> exclusions

    @Override
    void preHandle(HttpServletRequest request) {
        def authentication = authenticateIfPossible(request)
        if (!authentication) {
            throw unauthenticated()
        }
        authenticationContextConfigurer.setAuthentication(authentication)
        logAsInfo('Client has been authenticated', authentication)
    }

    private AuthenticationInfo authenticateIfPossible(HttpServletRequest request) {
        AuthenticationInfo result = null
        def certificatePayload = request.getHeader(certificatePayloadHeader)
        def tokenPayload = request.getHeader(tokenPayloadHeader)
        if (certificatePayload) {
            result = securityService.authenticateByCertificate(certificatePayload)
        } else if (tokenPayload) {
            result = securityService.authenticateByToken(tokenPayload)
        }
        result
    }

    private RuntimeException unauthenticated() {
        SecurityViolationException.unauthenticated(this)
    }
}
