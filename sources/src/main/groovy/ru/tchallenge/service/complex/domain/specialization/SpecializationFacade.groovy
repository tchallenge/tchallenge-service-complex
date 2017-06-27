package ru.tchallenge.service.complex.domain.specialization

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacade
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.convention.component.FacadeComponent

@CompileStatic
@FacadeComponent
class SpecializationFacade extends GenericFacade {

    @Autowired
    private SpecializationService specializationService

    Collection<EnumeratedInfo> getAll() {
        authenticated()
        return specializationService.getAll()
    }
}
