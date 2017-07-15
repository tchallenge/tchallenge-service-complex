package ru.tchallenge.service.complex.security.authentication

import groovy.transform.CompileStatic

@CompileStatic
interface AuthenticationContextConfigurer extends AuthenticationContext {

    void setAuthentication(AuthenticationInfo authentication)
}
