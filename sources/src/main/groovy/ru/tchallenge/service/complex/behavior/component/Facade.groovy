package ru.tchallenge.service.complex.behavior.component

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.security.authentication.AuthenticationContext

@CompileStatic
abstract class Facade {

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
