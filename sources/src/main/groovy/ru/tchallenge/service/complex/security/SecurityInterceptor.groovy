package ru.tchallenge.service.complex.security

import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMethod

import ru.tchallenge.service.complex.common.GenericInterceptor
import ru.tchallenge.service.complex.convention.component.InterceptorComponent
import ru.tchallenge.service.complex.security.authentication.AuthenticationContext
import ru.tchallenge.service.complex.security.authentication.AuthenticationService
import ru.tchallenge.service.complex.utility.routing.RouteSignature

@CompileStatic
@InterceptorComponent
class SecurityInterceptor extends GenericInterceptor {

    @Autowired
    protected AuthenticationContext authenticationContext

    @Autowired
    protected AuthenticationService authenticationService

    private Collection<RouteSignature> exclusions

    @Override
    boolean preHandle(HttpServletRequest request) {
        if (shouldBypass(request)) {
            return true
        }
        def certificatePayload = request.getHeader("tchallenge-security-certificate-payload")
        if (certificatePayload) {
            authenticateByCertificatePayload(certificatePayload)
            return true
        }
        def tokenPayload = request.getHeader("tchallenge-security-token-payload")
        if (tokenPayload) {
            authenticateByTokenPayload(tokenPayload)
            return true
        }
        throw new RuntimeException("unauthenticated")
    }

    @PostConstruct
    protected void init() {
        // TODO: collect exclusions based on NoAuthentication and RouteMethod annotations
        exclusions = [
                new RouteSignature(RequestMethod.POST, "/authentication")
        ]
    }

    private boolean shouldBypass(HttpServletRequest request) {
        return exclusions.contains(RouteSignature.fromRequest(request))
    }

    private void authenticateByCertificatePayload(String payload) {
        // TODO: implement authentication by certificate
        throw new UnsupportedOperationException()
    }

    private void authenticateByTokenPayload(String payload) {
        def authentication = authenticationService.createFromTokenPayload(payload)
        authenticationContext.setAuthentication(authentication)
    }
}
