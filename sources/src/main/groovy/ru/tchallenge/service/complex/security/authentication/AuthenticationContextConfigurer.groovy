package ru.tchallenge.service.complex.security.authentication

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericContextConfigurer

@CompileStatic
interface AuthenticationContextConfigurer extends AuthenticationContext, GenericContextConfigurer<AuthenticationInfo> {

}
