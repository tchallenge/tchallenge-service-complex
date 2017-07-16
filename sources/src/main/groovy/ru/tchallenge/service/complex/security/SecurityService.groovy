package ru.tchallenge.service.complex.security

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.security.authentication.AuthenticationInfo

@CompileStatic
interface SecurityService {

    AuthenticationInfo authenticateByCertificate(String payload)

    AuthenticationInfo authenticateByToken(String payload)
}
