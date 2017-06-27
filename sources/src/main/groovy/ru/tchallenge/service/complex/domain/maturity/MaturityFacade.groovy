package ru.tchallenge.service.complex.domain.maturity

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacade
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.convention.component.FacadeComponent

@CompileStatic
@FacadeComponent
class MaturityFacade extends GenericFacade {

    @Autowired
    private MaturityService maturityService

    Collection<EnumeratedInfo> getAll() {
        authenticated()
        return maturityService.getAll()
    }
}
