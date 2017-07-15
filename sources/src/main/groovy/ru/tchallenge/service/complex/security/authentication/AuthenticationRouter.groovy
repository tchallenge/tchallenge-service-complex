package ru.tchallenge.service.complex.security.authentication

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.common.GenericRouter
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RoutePost
import ru.tchallenge.service.complex.convention.security.NoAuthentication

@CompileStatic
@RouterComponent("/authentication")
class AuthenticationRouter extends GenericRouter {

    @Autowired
    protected AuthenticationFacade authenticationFacade

    @NoAuthentication
    @RoutePost
    AuthenticationInfo create(@RequestBody AuthenticationInvoice invoice) {
        authenticationFacade.create(invoice)
    }
}
