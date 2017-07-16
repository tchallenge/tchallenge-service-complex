package ru.tchallenge.service.complex.security.rescue

import groovy.transform.CompileStatic

@CompileStatic
interface RescuePayloadService {

    String create(String accountId)

    String restoreAccountId(String payload)
}
