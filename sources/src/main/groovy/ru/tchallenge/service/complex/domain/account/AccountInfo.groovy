package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.behavior.value.GenericInfoValue
import ru.tchallenge.service.complex.convention.value.Value

@CompileStatic
@Value
class AccountInfo extends GenericInfoValue {

    String id
    String login
}
