package ru.tchallenge.service.complex.utility.serialization

import groovy.transform.CompileStatic

import org.springframework.stereotype.Service

import ru.tchallenge.service.complex.common.GenericService

@CompileStatic
@Service
class EncryptionService extends GenericService {

    static String passwordHash(String password) {
        return password.hashCode()
    }
}
