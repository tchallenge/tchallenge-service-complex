package ru.tchallenge.service.complex.domain.specialization

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@PackageScope
@ServiceComponent
class SpecializationServiceBean extends GenericServiceBean implements SpecializationService {

    @Autowired
    SpecializationRepository specializationRepository

    @Override
    Collection<EnumeratedInfo> getAll() {
        enumerateds.all(specializationRepository)
    }
}
