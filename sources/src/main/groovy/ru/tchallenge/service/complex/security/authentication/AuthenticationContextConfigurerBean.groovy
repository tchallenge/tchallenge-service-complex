package ru.tchallenge.service.complex.security.authentication

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericContextBean
import ru.tchallenge.service.complex.convention.component.ContextComponent

@CompileStatic
@ContextComponent
class AuthenticationContextConfigurerBean extends GenericContextBean implements AuthenticationContextConfigurer {

    private volatile AuthenticationInfo authentication

    @Override
    Optional<AuthenticationInfo> getAuthentication() {
        Optional.ofNullable(authentication)
    }

    @Override
    void setAuthentication(AuthenticationInfo authentication) {
        this.authentication = authentication
    }
}
