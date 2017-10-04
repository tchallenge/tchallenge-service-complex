package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.common.GenericRouterBean
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteGet
import ru.tchallenge.service.complex.convention.routing.RoutePatch
import ru.tchallenge.service.complex.convention.routing.RoutePost
import ru.tchallenge.service.complex.convention.routing.RoutePut
import ru.tchallenge.service.complex.convention.security.NoAuthentication

@CompileStatic
@PackageScope
@RouterComponent('/accounts')
class AccountRouterBean extends GenericRouterBean {

    @Autowired
    AccountFacade accountFacade

    @RoutePost
    AccountInfo create(@RequestBody AccountInvoice invoice) {
        accountFacade.create(invoice)
    }

    @NoAuthentication
    @RoutePost('/claims')
    AccountInfo createAsClaim(@RequestBody AccountInvoice invoice) {
        accountFacade.createAsClaim(invoice)
    }

    @RouteGet('/{id}')
    AccountInfo getById(@PathVariable('id') String id) {
        accountFacade.getById(id)
    }

    @RouteGet
    SearchInfo<AccountInfo> search(AccountSearchInvoice invoice) {
        accountFacade.search(invoice)
    }

    @RoutePatch
    AccountInfo update(@RequestBody AccountInvoice invoice) {
        accountFacade.update(invoice)
    }

    @RoutePut('/status')
    AccountInfo updateStatus(@RequestBody AccountInvoice invoice) {
        accountFacade.updateStatus(invoice)
    }
}
