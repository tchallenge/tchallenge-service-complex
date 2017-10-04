package ru.tchallenge.service.complex.security.authentication

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.GenericContextConfigurerBean
import ru.tchallenge.service.complex.convention.component.RequestContextComponent

@CompileStatic
@PackageScope
@RequestContextComponent
class AuthenticationContextConfigurerBean extends GenericContextConfigurerBean<AuthenticationInfo> implements AuthenticationContextConfigurer {

}
