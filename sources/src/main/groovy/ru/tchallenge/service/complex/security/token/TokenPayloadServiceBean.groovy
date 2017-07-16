package ru.tchallenge.service.complex.security.token

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@PackageScope
@ServiceComponent
class TokenPayloadServiceBean extends GenericService implements TokenPayloadService {

    // TODO: implement a real encryption/decryption of token payloads
    // TODO: re-use this component for voucher payloads

    private static final String DELIMITER = ':'

    @Override
    String create(String accountId) {
        "${uuid}:${DELIMITER}:${accountId}"
    }

    @Override
    String restoreAccountId(String payload) {
        payload.tokenize(DELIMITER).last()
    }
}
