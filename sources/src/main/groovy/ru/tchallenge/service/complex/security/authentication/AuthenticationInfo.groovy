package ru.tchallenge.service.complex.security.authentication

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInfoValue
import ru.tchallenge.service.complex.domain.account.AccountInfo
import ru.tchallenge.service.complex.security.token.TokenInfo

@CompileStatic
class AuthenticationInfo extends GenericInfoValue {

    AccountInfo account
    TokenInfo token
}
