package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacadeBean
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.FacadeComponent

@CompileStatic
@PackageScope
@FacadeComponent
class AccountFacadeBean extends GenericFacadeBean implements AccountFacade {

    @Autowired
    AccountService accountService

    @Override
    AccountInfo create(AccountInvoice invoice) {
        if (!authenticatedUsermod()) {
            throw unauthorized()
        }
        accountService.create(invoice)
    }

    @Override
    AccountInfo createAsClaim(AccountInvoice invoice) {
        accountService.createAsClaim(invoice)
    }

    @Override
    AccountInfo getById(String id) {
        if (!authenticatedUsermodOrSelfReference(id)) {
            throw unauthorized()
        }
        accountService.getById(id)
    }

    @Override
    SearchInfo<AccountInfo> search(AccountSearchInvoice invoice) {
        if (!authenticatedUsermodOrCandidateViewer()) {
            throw unauthorized()
        }
        if (authenticatedUsermod()) {
            return accountService.search(invoice)
        }
        accountService.searchOnlyCandidates(invoice)
    }

    @Override
    AccountInfo update(AccountInvoice invoice) {
        if (!authenticatedUsermodOrSelfReference(invoice.id)) {
            throw unauthorized()
        }
        accountService.update(invoice)
    }

    @Override
    AccountInfo updateStatus(AccountInvoice invoice) {
        if (!authenticatedUsermod()) {
            throw unauthorized()
        }
        accountService.updateStatus(invoice)
    }

    private boolean authenticatedUsermod() {
        authenticatedEmployee('USERMOD')
    }

    private boolean authenticatedUsermodOrCandidateViewer() {
        authenticatedUsermod() || authenticatedEmployee('CANDMOD', 'CANDVIEW')
    }

    private boolean authenticatedUsermodOrSelfReference(String id) {
        authenticatedUsermod() || id == authenticated().id
    }
}
