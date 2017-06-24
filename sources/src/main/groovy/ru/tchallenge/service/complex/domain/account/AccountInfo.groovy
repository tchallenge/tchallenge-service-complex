package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInfoValue

@CompileStatic
class AccountInfo extends GenericInfoValue {

    String id
    String login
}
