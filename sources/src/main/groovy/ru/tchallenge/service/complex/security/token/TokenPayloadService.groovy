package ru.tchallenge.service.complex.security.token

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import static ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals.uuid

@CompileStatic
@ServiceComponent
class TokenPayloadService extends GenericService {

    private static final String DELIMITER = ":"

    static String fromAccountId(String accountId) {
        return uuid() + DELIMITER + accountId
    }

    static String restoreAccountId(String payload) {
        return payload.tokenize(DELIMITER).get(1)
    }
}
