package ru.tchallenge.service.complex.security.authentication

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import ru.tchallenge.service.complex.domain.account.AccountInfo
import ru.tchallenge.service.complex.security.token.TokenInfo

@CompileStatic
@Immutable
class AuthenticationInfo {

    AccountInfo account
    TokenInfo token
}
