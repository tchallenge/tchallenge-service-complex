package ru.tchallenge.service.complex.domain.specialization

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericRouterBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteGet

@CompileStatic
@PackageScope
@RouterComponent('/specializations')
class SpecializationRouterBean extends GenericRouterBean {

    @Autowired
    SpecializationFacade specializationFacade

    @RouteGet
    Collection<EnumeratedInfo> getAll() {
        specializationFacade.getAll()
    }
}
