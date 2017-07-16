package ru.tchallenge.service.complex.security.voucher

import groovy.transform.CompileStatic

@CompileStatic
interface VoucherPayloadService {

    String create(String accountId)

    String restoreAccountId(String payload)
}
