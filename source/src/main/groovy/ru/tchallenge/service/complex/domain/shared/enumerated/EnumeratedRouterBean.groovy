package ru.tchallenge.service.complex.domain.shared.enumerated

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable

import ru.tchallenge.service.complex.common.GenericRouterBean
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteGet

@CompileStatic
@PackageScope
@RouterComponent('/enumerateds')
class EnumeratedRouterBean extends GenericRouterBean {

    @Autowired
    EnumeratedFacade enumeratedFacade

    @RouteGet
    Collection<EnumeratedAggregationInfo> getAll() {
        enumeratedFacade.all
    }

    @RouteGet('/{type}')
    EnumeratedAggregationInfo getAggregationByType(@PathVariable('type') String type) {
        enumeratedFacade.getAggregationByType(type)
    }
}
