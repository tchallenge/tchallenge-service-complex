package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacade
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.FacadeComponent
import ru.tchallenge.service.complex.security.authentication.AuthenticationContext
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.all
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.all
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.all

@CompileStatic
@FacadeComponent
class AccountFacade extends GenericFacade {

    @Autowired
    protected AccountService accountService

    @Autowired
    protected AuthenticationContext authenticationContext

    AccountInfo create(AccountInvoice invoice) {
        if (!isAuthenticatedUsermod()) {
            throw unauthorized()
        }
        return accountService.create(invoice)
    }

    AccountInfo createAsClaim(AccountInvoice invoice) {
        return accountService.createAsClaim(invoice)
    }

    Collection<EnumeratedInfo> getAllRealms() {
        return accountService.allRealms
    }

    Collection<EnumeratedInfo> getAllStatuses() {
        return accountService.allStatuses
    }

    Collection<EnumeratedInfo> getAllVerifications() {
        return accountService.allVerifications
    }

    Collection<EnumeratedInfo> getAllEmployeeRoles() {
        return accountService.allEmployeeRoles
    }

    Collection<EnumeratedInfo> getAllRobotRoles() {
        return accountService.allRobotRoles
    }

    AccountInfo getById(String id) {
        if (!isAuthenticatedUsermodOrSelfReference(id)) {
            throw unauthorized()
        }
        return accountService.getById(id)
    }

    SearchInfo<AccountInfo> search(AccountSearchInvoice invoice) {
        if (!authenticatedEmployee("USERMOD", "CANDVIEW")) {
            throw unauthorized()
        }
        if (isAuthenticatedUsermod()) {
            return accountService.search(invoice)
        }
        return accountService.searchOnlyCandidates(invoice)
    }

    AccountInfo update(AccountInvoice invoice) {
        if (!isAuthenticatedUsermodOrSelfReference(invoice.id)) {
            throw unauthorized()
        }
        return accountService.update(invoice)
    }

    AccountInfo updateStatus(AccountInvoice invoice) {
        if (!isAuthenticatedUsermod()) {
            throw unauthorized()
        }
        return accountService.updateStatus(invoice)
    }

    private boolean isAuthenticatedUsermod() {
        return authenticatedEmployee("USERMOD")
    }

    private boolean isAuthenticatedUsermodOrSelfReference(String id) {
        return authenticatedUsermod || id == authenticated().id
    }
}
