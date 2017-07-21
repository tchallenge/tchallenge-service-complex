package ru.tchallenge.service.complex.domain.maturity

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@PackageScope
@ServiceComponent
class MaturityServiceBean extends GenericServiceBean implements MaturityService {

    @Autowired
    MaturityRepository maturityRepository

    @Override
    Collection<EnumeratedInfo> getAll() {
        enumerateds.all(maturityRepository)
    }
}
