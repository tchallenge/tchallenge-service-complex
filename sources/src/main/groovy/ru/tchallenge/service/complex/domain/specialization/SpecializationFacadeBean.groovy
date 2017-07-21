package ru.tchallenge.service.complex.domain.specialization

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacadeBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.convention.component.FacadeComponent

@CompileStatic
@PackageScope
@FacadeComponent
class SpecializationFacadeBean extends GenericFacadeBean implements SpecializationFacade {

    @Autowired
    SpecializationService specializationService

    @Override
    Collection<EnumeratedInfo> getAll() {
        specializationService.getAll()
    }
}
