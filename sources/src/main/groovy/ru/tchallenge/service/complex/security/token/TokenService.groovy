package ru.tchallenge.service.complex.security.token

import groovy.transform.CompileStatic

@CompileStatic
interface TokenService {

    TokenInfo create(String accountId)

    TokenInfo get(String payload)

    void remove(String payload)

    void removeAllForAccount(String payload)
}
