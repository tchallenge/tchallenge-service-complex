package ru.tchallenge.service.complex.security.token

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericStorageLocal
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@ServiceComponent
class TokenStorageLocal extends GenericStorageLocal implements TokenStorage {

}
