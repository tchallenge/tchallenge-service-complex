package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.security.authentication.AuthenticationContext

@CompileStatic
abstract class GenericFacade extends GenericComponent {

    @Autowired
    protected AuthenticationContext authenticationContext

    protected def authenticated() {
        def authentication = authenticationContext.authentication
        if (authentication == null) {
            throw new RuntimeException("not authenticated")
        }
        return authentication
    }
}
