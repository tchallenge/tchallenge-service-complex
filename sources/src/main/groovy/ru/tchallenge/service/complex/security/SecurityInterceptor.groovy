package ru.tchallenge.service.complex.security

import javax.servlet.http.HttpServletRequest

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericInterceptor
import ru.tchallenge.service.complex.convention.component.InterceptorComponent
import ru.tchallenge.service.complex.security.authentication.AuthenticationContext
import ru.tchallenge.service.complex.security.authentication.AuthenticationService

@CompileStatic
@InterceptorComponent
class SecurityInterceptor extends GenericInterceptor {

    @Autowired
    protected AuthenticationContext authenticationContext

    @Autowired
    protected AuthenticationService authenticationService

    @Override
    boolean preHandle(HttpServletRequest request) {
        if (request.requestURI == "/authentication") {
            return true
        }
        def tokenPayload = request.getHeader("tchallenge-security-token-payload")
        if (tokenPayload) {
            def authentication = authenticationService.createFromTokenPayload(tokenPayload)
            authenticationContext.setAuthentication(authentication)
            return true
        }
        throw new RuntimeException("unauthenticated")
    }
}
