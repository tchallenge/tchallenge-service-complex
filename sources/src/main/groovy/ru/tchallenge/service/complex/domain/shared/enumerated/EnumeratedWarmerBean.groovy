package ru.tchallenge.service.complex.domain.shared.enumerated

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericWarmerBean
import ru.tchallenge.service.complex.convention.component.WarmerComponent

@CompileStatic
@PackageScope
@WarmerComponent
class EnumeratedWarmerBean extends GenericWarmerBean implements EnumeratedWarmer {

    @Autowired
    EnumeratedService enumeratedService

    @Override
    void run() {
        enumeratedService.all
    }
}
