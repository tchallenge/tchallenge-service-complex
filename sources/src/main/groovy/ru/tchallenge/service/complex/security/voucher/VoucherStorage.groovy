package ru.tchallenge.service.complex.security.voucher

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericStorage

@CompileStatic
interface VoucherStorage extends GenericStorage<VoucherInfo, String> {

}
