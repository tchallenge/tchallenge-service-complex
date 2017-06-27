package ru.tchallenge.service.complex.domain.maturity

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericRouter
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteGet

@CompileStatic
@RouterComponent("/maturities")
class MaturityRouter extends GenericRouter {

    @Autowired
    protected MaturityFacade maturityFacade

    @RouteGet
    Collection<EnumeratedInfo> getAll() {
        return maturityFacade.getAll()
    }
}
