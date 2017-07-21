package ru.tchallenge.service.complex.domain.maturity

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericRouterBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteGet

@CompileStatic
@PackageScope
@RouterComponent('/maturities')
class MaturityRouterBean extends GenericRouterBean {

    @Autowired
    MaturityFacade maturityFacade

    @RouteGet
    Collection<EnumeratedInfo> getAll() {
        maturityFacade.getAll()
    }
}
