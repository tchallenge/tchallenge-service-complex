package ru.tchallenge.service.complex.security.voucher

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import com.fasterxml.jackson.annotation.JsonIgnore

import ru.tchallenge.service.complex.reliability.violation.BaseViolationInfo
import ru.tchallenge.service.complex.reliability.violation.SecurityViolationInfo
import ru.tchallenge.service.complex.reliability.violation.ViolationInfo

@CompileStatic
@Immutable
final class VoucherViolationInfo implements ViolationInfo {

    static VoucherViolationInfo expired(VoucherInfo voucher) {
        def base = new SecurityViolationInfo(
                base: new BaseViolationInfo(
                        category: SecurityViolationInfo.CATEGORY,
                        description: 'Voucher has been expired',
                        textcode: "${SecurityViolationInfo.TEXTCODE_PREFIX}.VOUCHER.EXPIRED"
                )
        )
        new VoucherViolationInfo(
                base: base,
                payload: voucher.payload,
                voucher: voucher
        )
    }

    static VoucherViolationInfo unknown(String payload) {
        def base = new SecurityViolationInfo(
                base: new BaseViolationInfo(
                        category: SecurityViolationInfo.CATEGORY,
                        description: 'Voucher payload has not been recognized',
                        textcode: "${SecurityViolationInfo.TEXTCODE_PREFIX}.VOUCHER.UNKNOWN"
                )
        )
        new VoucherViolationInfo(
                base: base,
                payload: payload
        )
    }

    @Delegate
    @JsonIgnore
    SecurityViolationInfo base

    String payload
    VoucherInfo voucher
}
