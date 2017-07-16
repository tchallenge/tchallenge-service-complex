package ru.tchallenge.service.complex.security.rescue

import groovy.transform.CompileStatic

@CompileStatic
interface RescueService {

    RescueInfo create(String accountId)

    RescueInfo getAndRemove(String payload)

    void remove(String payload)

    void removeAllForAccount(String payload)
}
