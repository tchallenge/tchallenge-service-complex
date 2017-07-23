package ru.tchallenge.service.complex.domain.shared.enumerated

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.GenericContextConfigurerBean
import ru.tchallenge.service.complex.convention.component.ApplicationContextComponent

@CompileStatic
@PackageScope
@ApplicationContextComponent
class EnumeratedContextConfigurerBean extends GenericContextConfigurerBean<EnumeratedAggregationInfo> implements EnumeratedContextConfigurer {

}
