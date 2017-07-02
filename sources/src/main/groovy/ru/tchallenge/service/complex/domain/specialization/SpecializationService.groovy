package ru.tchallenge.service.complex.domain.specialization

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.all

@CompileStatic
@ServiceComponent
class SpecializationService extends GenericService {

    @Autowired
    protected SpecializationRepository specializationRepository

    Collection<EnumeratedInfo> getAll() {
        return all(specializationRepository)
    }
}
