package ru.tchallenge.service.complex.security.authentication

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacade
import ru.tchallenge.service.complex.convention.component.FacadeComponent

@CompileStatic
@FacadeComponent
class AuthenticationFacade extends GenericFacade {

    @Autowired
    protected AuthenticationService authenticationService

    AuthenticationInfo create(AuthenticationInvoice invoice) {
        authenticationService.createFromLoginAndPassword(invoice.login, invoice.password)
    }
}
