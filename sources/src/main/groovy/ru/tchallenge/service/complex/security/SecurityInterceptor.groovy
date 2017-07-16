package ru.tchallenge.service.complex.security

import groovy.transform.CompileStatic

import javax.servlet.http.HttpServletRequest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMethod

import ru.tchallenge.service.complex.common.GenericInterceptorBean
import ru.tchallenge.service.complex.convention.component.InterceptorComponent
import ru.tchallenge.service.complex.reliability.exception.SecurityViolationException
import ru.tchallenge.service.complex.security.authentication.AuthenticationContextConfigurer
import ru.tchallenge.service.complex.security.authentication.AuthenticationService
import ru.tchallenge.service.complex.utility.routing.RouteSignature

@CompileStatic
@InterceptorComponent
class SecurityInterceptor extends GenericInterceptorBean {

    @Autowired
    protected AuthenticationContextConfigurer authenticationContextConfigurer

    @Autowired
    protected AuthenticationService authenticationService

    protected Collection<RouteSignature> exclusions

    @Override
    void preHandle(HttpServletRequest request) {
        // TODO: re-implement to get rid off intermediate returns
        def certificatePayload = request.getHeader("tchallenge-security-certificate-payload")
        if (certificatePayload) {
            authenticateByCertificate(certificatePayload)
            return
        }
        def tokenPayload = request.getHeader("tchallenge-security-token-payload")
        if (tokenPayload) {
            authenticateByToken(tokenPayload)
            return
        }
        throw unauthenticated()
    }

    @Override
    protected void init() {
        // TODO: collect exclusions based on NoAuthentication and RouteMethod annotations
        super.init()
        exclusions = [
                new RouteSignature(
                        method: RequestMethod.POST,
                        uri: "/authentication"
                ),
                new RouteSignature(
                        method: RequestMethod.POST,
                        uri: "/accounts/claims"
                ),
                new RouteSignature(
                        method: RequestMethod.GET,
                        pattern: true,
                        uri: "/events/.+"
                )
        ]
    }

    private void authenticateByCertificate(String payload) {
        // TODO: implement authentication by certificate
        throw new UnsupportedOperationException()
    }

    private void authenticateByToken(String payload) {
        def authentication = authenticationService.createFromTokenPayload(payload)
        authenticationContextConfigurer.setAuthentication(authentication)
    }

    private RuntimeException unauthenticated() {
        SecurityViolationException.unauthenticated(this)
    }
}
