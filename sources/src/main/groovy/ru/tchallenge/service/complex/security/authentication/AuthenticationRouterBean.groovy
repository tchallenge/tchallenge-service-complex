package ru.tchallenge.service.complex.security.authentication

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.common.GenericRouterBean
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteDelete
import ru.tchallenge.service.complex.convention.routing.RoutePost
import ru.tchallenge.service.complex.convention.security.NoAuthentication

@CompileStatic
@RouterComponent('/authentication')
class AuthenticationRouterBean extends GenericRouterBean {

    @Autowired
    AuthenticationFacade authenticationFacade

    @NoAuthentication
    @RoutePost
    AuthenticationInfo create(@RequestBody AuthenticationInvoice invoice) {
        authenticationFacade.create(invoice)
    }

    @RouteDelete
    void remove() {
        // TODO: implement this method
        throw new UnsupportedOperationException('authentication removal')
    }

    @RouteDelete('/all')
    void removeAll() {
        // TODO: implement this method
        throw new UnsupportedOperationException('all authentications removal')
    }
}
