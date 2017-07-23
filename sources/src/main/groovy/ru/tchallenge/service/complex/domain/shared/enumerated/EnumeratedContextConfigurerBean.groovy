package ru.tchallenge.service.complex.domain.shared.enumerated

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.GenericContextBean
import ru.tchallenge.service.complex.convention.component.ApplicationContextComponent

@CompileStatic
@PackageScope
@ApplicationContextComponent
class EnumeratedContextConfigurerBean extends GenericContextBean implements EnumeratedContextConfigurer {

    private volatile Map<String, EnumeratedAggregationInfo> aggregations

    @Override
    Map<String, EnumeratedAggregationInfo> getAggregations() {
        aggregations
    }

    @Override
    void setAggregations(Map<String, EnumeratedAggregationInfo> aggregations) {
        this.aggregations = aggregations
    }
}
