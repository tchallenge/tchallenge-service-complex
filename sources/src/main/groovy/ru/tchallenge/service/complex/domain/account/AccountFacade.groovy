package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacade
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.common.search.SearchInvoice
import ru.tchallenge.service.complex.convention.component.FacadeComponent

@CompileStatic
@FacadeComponent
class AccountFacade extends GenericFacade {

    @Autowired
    protected AccountService accountService

    SearchInfo<AccountInfo> search(SearchInvoice<AccountInvoice> invoice) {
        return accountService.search(invoice)
    }
}
