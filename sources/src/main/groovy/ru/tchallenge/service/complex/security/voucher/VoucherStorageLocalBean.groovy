package ru.tchallenge.service.complex.security.voucher

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.GenericStorageLocalBean
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@PackageScope
@ServiceComponent
class VoucherStorageLocalBean extends GenericStorageLocalBean implements VoucherStorage {

}
