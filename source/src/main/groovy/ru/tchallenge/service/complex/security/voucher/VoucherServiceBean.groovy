package ru.tchallenge.service.complex.security.voucher

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import java.time.Duration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.reliability.exception.SecurityViolationException
import ru.tchallenge.service.complex.security.shared.PayloadService

@CompileStatic
@PackageScope
@ServiceComponent
class VoucherServiceBean extends GenericServiceBean implements VoucherService {

    @Autowired
    PayloadService payloadService

    @Autowired
    VoucherStorage voucherStorage

    @Value('${tchallenge.security.voucher.expirationInMinutes}')
    Integer expirationInMinutes

    @Override
    VoucherInfo create(String accountId) {
        // TODO: possibly, remove all other vouchers for the account
        def $now = now
        def $result = new VoucherInfo(
                id: uuid,
                payload: payloadService.create(accountId),
                createdAt: $now,
                expiresAt: $now + Duration.ofMinutes(expirationInMinutes)
        )
        voucherStorage.put($result.payload, $result)
        logAsInfo('New voucher has been created', $result)
        $result
    }

    @Override
    VoucherInfo getAndRemove(String payload) {
        def $result = voucherStorage
                .get(payload)
                .orElseThrow { voucherUnknown(payload) }
        if ($result.expired) {
            throw voucherExpired($result)
        }
        remove(payload)
        logAsDebug('Voucher has been retrieved', payload)
        $result
    }

    @Override
    void remove(String payload) {
        voucherStorage.remove(payload)
        logAsDebug('Voucher has been removed', payload)
    }

    @Override
    void removeAllForAccount(String payload) {
        // TODO: implement this method
        throw new UnsupportedOperationException('Removal of all vouchers for a given account is not yet implemented')
    }

    private SecurityViolationException voucherExpired(VoucherInfo voucher) {
        SecurityViolationException.of(this, VoucherViolationInfo.expired(voucher))
    }

    private SecurityViolationException voucherUnknown(String payload) {
        SecurityViolationException.of(this, VoucherViolationInfo.unknown(payload))
    }
}
