package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.common.GenericRouter
import ru.tchallenge.service.complex.common.search.SearchAware
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.common.search.GenericSearchInvoice
import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteGet
import ru.tchallenge.service.complex.convention.routing.RoutePatch
import ru.tchallenge.service.complex.convention.routing.RoutePost
import ru.tchallenge.service.complex.convention.routing.RoutePut
import ru.tchallenge.service.complex.convention.security.NoAuthentication

@CompileStatic
@RouterComponent("/accounts")
class AccountRouter extends GenericRouter {

    @Autowired
    protected AccountFacade accountFacade

    @RoutePost
    AccountInfo create(@RequestBody AccountInvoice invoice) {
        return accountFacade.create(invoice)
    }

    @NoAuthentication
    @RoutePost("/claims")
    AccountInfo createAsClaim(@RequestBody AccountInvoice invoice) {
        return accountFacade.createAsClaim(invoice)
    }

    @RouteGet("/{id}")
    AccountInfo getByTextcode(@PathVariable("id") String id) {
        return accountFacade.get(id)
    }

    @RouteGet
    SearchInfo<AccountInfo> search(AccountSearchInvoice invoice) {
        return accountFacade.search(ensureOrderingAndPaging(invoice))
    }

    @RoutePatch
    AccountInfo update(@RequestBody AccountInvoice invoice) {
        return accountFacade.update(invoice)
    }

    @RoutePut("/status")
    AccountInfo updateStatus(@RequestBody AccountInvoice invoice) {
        return accountFacade.updateStatus(invoice)
    }

    private static AccountSearchInvoice ensureOrderingAndPaging(AccountSearchInvoice invoice) {
        return invoice.with {
            orderDescending = invoice.orderDescending ?: false
            orderProperties = invoice.orderProperties ?: [] as Collection<String>
            pageNumber = invoice.pageNumber ?: 1
            pageSize = invoice.pageSize ?: 10
            it
        }
    }
}
