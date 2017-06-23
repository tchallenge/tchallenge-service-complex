package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.convention.component.RouterComponent
import ru.tchallenge.service.complex.convention.routing.RouteGet
import ru.tchallenge.service.complex.convention.routing.RoutePost
import ru.tchallenge.service.complex.convention.routing.RoutePut

@CompileStatic
@RouterComponent("/accounts")
class AccountRouter {

    @RoutePost
    def create(@RequestBody AccountInvoice invoice) {
        return new AccountInfo(
                id: invoice.id,
                login: invoice.login
        )
    }

    @RouteGet("/{id}")
    def getByTextcode(@PathVariable("id") String id) {
        return new AccountInfo(id: id)
    }

    @RouteGet
    def search(AccountSearchInvoice invoice) {
        return [
                new AccountInfo(),
                new AccountInfo(),
                new AccountInfo()
        ]
    }

    @RoutePut
    def update(@RequestBody AccountInvoice invoice) {
        return new AccountInfo(
                id: invoice.id,
                login: invoice.login
        )
    }
}
