package ru.tchallenge.service.complex.security.voucher

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@PackageScope
@ServiceComponent
class VoucherPayloadServiceBean extends GenericService implements VoucherPayloadService {

    // TODO: implement a real encryption/decryption of voucher payloads
    // TODO: re-use this component for token payloads

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
