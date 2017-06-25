package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacade
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.common.search.SearchInvoice
import ru.tchallenge.service.complex.convention.component.FacadeComponent
import ru.tchallenge.service.complex.security.authentication.AuthenticationContext

@CompileStatic
@FacadeComponent
class AccountFacade extends GenericFacade {

    @Autowired
    protected AccountService accountService

    @Autowired
    protected AuthenticationContext authenticationContext

    AccountInfo get(String id) {
        if (!authenticatedEmployee("USERMOD") && id != authenticated().id) {
            throw unauthorized()
        }
        return accountService.getById(id)
    }

    SearchInfo<AccountInfo> search(SearchInvoice<AccountInvoice> invoice) {
        if (!authenticatedEmployee("USERMOD")) {
            throw unauthorized()
        }
        return accountService.search(invoice)
    }
}
