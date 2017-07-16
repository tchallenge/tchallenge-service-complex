package ru.tchallenge.service.complex.security.voucher

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.GenericStorageLocal
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@PackageScope
@ServiceComponent
class VoucherStorageLocalBean extends GenericStorageLocal implements VoucherStorage {

}
