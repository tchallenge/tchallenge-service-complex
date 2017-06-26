package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.common.GenericRouter
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.common.search.SearchInvoice
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteGet
import ru.tchallenge.service.complex.convention.routing.RoutePost
import ru.tchallenge.service.complex.convention.routing.RoutePut

@CompileStatic
@RouterComponent("/accounts")
class AccountRouter extends GenericRouter {

    @Autowired
    protected AccountFacade accountFacade

    @RoutePost
    AccountInfo create(@RequestBody AccountInvoice invoice) {
        return new AccountInfo(
                id: invoice.id,
                login: invoice.login
        )
    }

    @RouteGet("/{id}")
    AccountInfo getByTextcode(@PathVariable("id") String id) {
        return accountFacade.get(id)
    }

    @RouteGet
    SearchInfo<AccountInfo> search(SearchInvoice<AccountFilterInvoice> invoice) {
        return accountFacade.search(invoice)
    }

    @RoutePut
    AccountInfo update(@RequestBody AccountInvoice invoice) {
        return new AccountInfo(
                id: invoice.id,
                login: invoice.login
        )
    }
}
