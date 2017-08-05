package ru.tchallenge.service.complex.domain.sample

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.common.GenericRouterBean
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteGet
import ru.tchallenge.service.complex.convention.routing.RoutePatch
import ru.tchallenge.service.complex.convention.routing.RoutePost
import ru.tchallenge.service.complex.validation.groups.Create
import ru.tchallenge.service.complex.validation.groups.Edit
import ru.tchallenge.service.complex.validation.groups.Status

@CompileStatic
@PackageScope
@RouterComponent('/samples')
class SampleRouterBean extends GenericRouterBean {

    @Autowired
    SampleService sampleService

    @RoutePost
    SampleInfo create(@Validated(Create) @RequestBody SampleInvoice invoice) {
        sampleService.create(invoice)
    }

    @RouteGet('/{id}')
    SampleInfo getById(@PathVariable('id') String id) {
        sampleService.getById(id)
    }

    @RoutePatch('/{id}')
    SampleInfo update(@Validated(Edit) @RequestBody SampleInvoice invoice) {
        sampleService.update(invoice)
    }

    @RoutePatch('/{id}/status')
    SampleInfo updateStatus(@Validated(Status) @RequestBody SampleInvoice invoice) {
        sampleService.updateStatus(invoice)
    }
}
