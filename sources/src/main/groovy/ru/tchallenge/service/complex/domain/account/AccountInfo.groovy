package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.behavior.value.InfoValue
import ru.tchallenge.service.complex.convention.value.Value

@CompileStatic
@Value
class AccountInfo extends InfoValue {

    String id
    String login
}
