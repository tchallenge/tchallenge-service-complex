package ru.tchallenge.service.complex.utility.encryption

import groovy.transform.CompileStatic

@CompileStatic
interface EncryptionService {

    String hash(String input)

    boolean verify(String input, String hash)
}
