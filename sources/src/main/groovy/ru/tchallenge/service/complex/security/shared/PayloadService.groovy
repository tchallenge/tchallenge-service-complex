package ru.tchallenge.service.complex.security.shared

import groovy.transform.CompileStatic

@CompileStatic
interface PayloadService {

    String create(String accountId)

    String restoreAccountId(String payload)
}
