package ru.tchallenge.service.complex.security.rescue

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.common.GenericRouterBean
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RoutePost
import ru.tchallenge.service.complex.convention.security.NoAuthentication

@CompileStatic
@PackageScope
@RouterComponent('/rescues')
class RescueRouterBean extends GenericRouterBean {

    @Autowired
    RescueFacade rescueFacade

    @NoAuthentication
    @RoutePost
    void create(@RequestBody RescueInvoice invoice) {
        rescueFacade.create(invoice)
    }
}
