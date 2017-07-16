package ru.tchallenge.service.complex.security.rescue

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.GenericStorageLocal
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@PackageScope
@ServiceComponent
class RescueStorageLocalBean extends GenericStorageLocal implements RescueStorage {

}
