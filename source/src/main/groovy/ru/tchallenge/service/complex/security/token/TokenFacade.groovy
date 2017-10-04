package ru.tchallenge.service.complex.security.token

import groovy.transform.CompileStatic

@CompileStatic
interface TokenFacade {

    TokenInfo create(TokenInvoice invoice)

    TokenInfo get()

    void remove()

    void removeAllForAccount()
}
