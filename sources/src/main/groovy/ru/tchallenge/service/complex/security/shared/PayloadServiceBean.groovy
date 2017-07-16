package ru.tchallenge.service.complex.security.shared

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@PackageScope
@ServiceComponent
class PayloadServiceBean extends GenericService implements PayloadService {

    // TODO: implement a real encryption/decryption of token payloads

    private static final String DELIMITER = ':'

    @Override
    String create(String accountId) {
        "${uuid}${DELIMITER}${accountId}"
    }

    @Override
    String restoreAccountId(String payload) {
        payload.tokenize(DELIMITER).last()
    }
}
