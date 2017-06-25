package ru.tchallenge.service.complex.security.token

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericStorage

@CompileStatic
interface TokenStorage extends GenericStorage<TokenInfo, String> {

}
