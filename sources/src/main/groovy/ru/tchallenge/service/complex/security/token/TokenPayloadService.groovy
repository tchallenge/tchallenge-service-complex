package ru.tchallenge.service.complex.security.token

import groovy.transform.CompileStatic

@CompileStatic
interface TokenPayloadService {

    String create(String accountId)

    String restoreAccountId(String payload)
}
