package ru.tchallenge.service.complex.domain.sample

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.common.GenericRouterBean
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteGet
import ru.tchallenge.service.complex.convention.routing.RoutePatch
import ru.tchallenge.service.complex.convention.routing.RoutePost

@CompileStatic
@PackageScope
@RouterComponent('/samples')
class SampleRouterBean extends GenericRouterBean {

    @Autowired
    SampleService sampleService

    @RoutePost
    SampleInfo create(@RequestBody SampleRawInvoice invoice) {
        sampleService.create(invoice)
    }

    @RouteGet('/{id}')
    SampleInfo getById(@PathVariable('id') String id) {
        sampleService.getById(id)
    }

    @RoutePatch('/{id}')
    SampleInfo update(@RequestBody SampleRawInvoice invoice) {
        sampleService.update(invoice)
    }
}
