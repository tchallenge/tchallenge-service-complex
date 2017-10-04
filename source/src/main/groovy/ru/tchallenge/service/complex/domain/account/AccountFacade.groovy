package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.search.SearchInfo

@CompileStatic
interface AccountFacade {

    AccountInfo create(AccountInvoice invoice)

    AccountInfo createAsClaim(AccountInvoice invoice)

    AccountInfo getById(String id)

    SearchInfo<AccountInfo> search(AccountSearchInvoice invoice)

    AccountInfo update(AccountInvoice invoice)

    AccountInfo updateStatus(AccountInvoice invoice)
}
