package ru.tchallenge.service.complex.domain.maturity

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacadeBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.convention.component.FacadeComponent

@CompileStatic
@FacadeComponent
class MaturityFacade extends GenericFacadeBean {

    @Autowired
    private MaturityService maturityService

    Collection<EnumeratedInfo> getAll() {
        authenticated()
        return maturityService.getAll()
    }
}
