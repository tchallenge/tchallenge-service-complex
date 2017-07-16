package ru.tchallenge.service.complex.security.token

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.common.GenericRouterBean
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteDelete
import ru.tchallenge.service.complex.convention.routing.RouteGet
import ru.tchallenge.service.complex.convention.routing.RoutePost
import ru.tchallenge.service.complex.convention.security.NoAuthentication

@CompileStatic
@PackageScope
@RouterComponent('/tokens')
class TokenRouterBean extends GenericRouterBean {

    @Autowired
    TokenFacade tokenFacade

    @NoAuthentication
    @RoutePost
    TokenInfo create(@RequestBody TokenInvoice invoice) {
        tokenFacade.create(invoice)
    }

    @RouteGet('/current')
    TokenInfo get() {
        tokenFacade.get()
    }

    @RouteDelete
    void remove() {
        tokenFacade.remove()
    }

    @RouteDelete
    void removeAllForAccount() {
        tokenFacade.removeAllForAccount()
    }
}
