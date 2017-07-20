package ru.tchallenge.service.complex.domain.maturity

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.all

@CompileStatic
@ServiceComponent
class MaturityService extends GenericServiceBean {

    @Autowired
    protected MaturityRepository maturityRepository

    Collection<EnumeratedInfo> getAll() {
        return all(maturityRepository)
    }
}
