package ru.tchallenge.service.complex.security.voucher

import groovy.transform.CompileStatic

@CompileStatic
interface VoucherService {

    VoucherInfo create(String accountId)

    VoucherInfo getAndRemove(String payload)

    void remove(String payload)

    void removeAllForAccount(String payload)
}
