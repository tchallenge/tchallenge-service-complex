package ru.tchallenge.service.complex.domain.maturity

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacadeBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.convention.component.FacadeComponent

@CompileStatic
@PackageScope
@FacadeComponent
class MaturityFacadeBean extends GenericFacadeBean implements MaturityFacade {

    @Autowired
    MaturityService maturityService

    @Override
    Collection<EnumeratedInfo> getAll() {
        maturityService.getAll()
    }
}
