package ru.tchallenge.service.complex.domain.shared.enumerated

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacadeBean
import ru.tchallenge.service.complex.convention.component.FacadeComponent

@CompileStatic
@PackageScope
@FacadeComponent
class EnumeratedFacadeBean extends GenericFacadeBean implements EnumeratedFacade {

    @Autowired
    EnumeratedService enumeratedService

    @Override
    Collection<EnumeratedAggregationInfo> getAll() {
        enumeratedService.all
    }

    @Override
    EnumeratedAggregationInfo getAggregationByType(String type) {
        enumeratedService.getAggregationByType(type)
    }
}
