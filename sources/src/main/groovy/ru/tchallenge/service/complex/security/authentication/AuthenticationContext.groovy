package ru.tchallenge.service.complex.security.authentication

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.behavior.component.Context
import ru.tchallenge.service.complex.convention.component.ContextComponent

@CompileStatic
@ContextComponent
class AuthenticationContext implements Context {

    AuthenticationInfo authentication
}
