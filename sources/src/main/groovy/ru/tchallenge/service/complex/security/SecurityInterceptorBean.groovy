package ru.tchallenge.service.complex.security

import groovy.transform.CompileStatic

import javax.servlet.http.HttpServletRequest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RequestMethod

import ru.tchallenge.service.complex.common.GenericInterceptorBean
import ru.tchallenge.service.complex.convention.component.InterceptorComponent
import ru.tchallenge.service.complex.reliability.exception.NotSupportedException
import ru.tchallenge.service.complex.reliability.exception.SecurityViolationException
import ru.tchallenge.service.complex.security.authentication.AuthenticationContextConfigurer
import ru.tchallenge.service.complex.security.authentication.AuthenticationInfo
import ru.tchallenge.service.complex.security.authentication.AuthenticationService
import ru.tchallenge.service.complex.utility.routing.RouteSignature

@CompileStatic
@InterceptorComponent
class SecurityInterceptorBean extends GenericInterceptorBean implements SecurityInterceptor {

    @Autowired
    AuthenticationContextConfigurer authenticationContextConfigurer

    @Autowired
    AuthenticationService authenticationService

    @Value('${tchallenge.security.headers.certificatePayload}')
    String certificatePayloadHeader

    @Value('${tchallenge.security.headers.tokenPayload}')
    String tokenPayloadHeader

    // TODO: inject?
    protected Collection<RouteSignature> exclusions

    @Override
    void preHandle(HttpServletRequest request) {
        def authentication = authenticateIfPossible(request)
        if (!authentication) {
            throw unauthenticated()
        }
        authenticationContextConfigurer.setAuthentication(authentication)
        logAsInfo('Client has been authenticated', authentication)
    }

    @Override
    protected void init() {
        // TODO: collect exclusions based on NoAuthentication and RouteMethod annotations
        super.init()
        exclusions = [
                new RouteSignature(
                        method: RequestMethod.POST,
                        uri: '/authentication'
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

    private AuthenticationInfo authenticateIfPossible(HttpServletRequest request) {
        AuthenticationInfo result = null
        def certificatePayload = request.getHeader(certificatePayloadHeader)
        def tokenPayload = request.getHeader(tokenPayloadHeader)
        if (certificatePayload) {
            throw NotSupportedException.expectedSince(
                    this,
                    '1.3.x',
                    'Authentication by certificate is not yet supported'
            )
        } else if (tokenPayload) {
            result = authenticationService.createFromTokenPayload(tokenPayload)
        }
        result
    }

    private RuntimeException unauthenticated() {
        SecurityViolationException.unauthenticated(this)
    }
}
