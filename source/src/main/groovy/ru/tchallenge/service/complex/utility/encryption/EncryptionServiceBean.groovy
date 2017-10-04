package ru.tchallenge.service.complex.utility.encryption

import groovy.transform.CompileStatic

import org.mindrot.jbcrypt.BCrypt

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@ServiceComponent
class EncryptionServiceBean extends GenericServiceBean implements EncryptionService {

    @Override
    String hash(String input) {
        BCrypt.hashpw(input, BCrypt.gensalt())
    }

    @Override
    boolean verify(String input, String hash) {
        BCrypt.checkpw(input, hash)
    }
}
