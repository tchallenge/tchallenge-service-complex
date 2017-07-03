package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

import ru.tchallenge.service.complex.common.GenericRouter
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.search.SearchInfo
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

    @RouteGet("/realms")
    Collection<EnumeratedInfo> getAllRealms() {
        return accountFacade.allRealms
    }

    @RouteGet("/statuses")
    Collection<EnumeratedInfo> getAllStatuses() {
        return accountFacade.allStatuses
    }

    @RouteGet("/verifications")
    Collection<EnumeratedInfo> getAllVerifications() {
        return accountFacade.allVerifications
    }

    @RouteGet("/employees/roles")
    Collection<EnumeratedInfo> getAllEmployeeRoles() {
        return accountFacade.allEmployeeRoles
    }

    @RouteGet("/robots/roles")
    Collection<EnumeratedInfo> getAllRobotRoles() {
        return accountFacade.allRobotRoles
    }

    @RouteGet("/{id}")
    AccountInfo getById(@PathVariable("id") String id) {
        return accountFacade.getById(id)
    }

    @RouteGet
    SearchInfo<AccountInfo> search(AccountSearchInvoice invoice) {
        return accountFacade.search(invoice)
    }

    @RoutePatch
    AccountInfo update(@RequestBody AccountInvoice invoice) {
        return accountFacade.update(invoice)
    }

    @RoutePut("/status")
    AccountInfo updateStatus(@RequestBody AccountInvoice invoice) {
        return accountFacade.updateStatus(invoice)
    }
}
