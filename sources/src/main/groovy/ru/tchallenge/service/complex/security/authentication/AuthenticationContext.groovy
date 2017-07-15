package ru.tchallenge.service.complex.security.authentication

import groovy.transform.CompileStatic

@CompileStatic
interface AuthenticationContext {

    Optional<AuthenticationInfo> getAuthentication()
}
