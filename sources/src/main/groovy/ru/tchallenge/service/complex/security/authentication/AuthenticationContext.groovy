package ru.tchallenge.service.complex.security.authentication

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericContext
import ru.tchallenge.service.complex.convention.component.ContextComponent

@CompileStatic
@ContextComponent
class AuthenticationContext extends GenericContext {

    AuthenticationInfo authentication
}
