package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

@CompileStatic
interface AccountMapper {

    Account asEntity(AccountInvoice invoice)

    Account asEntity(Account entity, AccountInvoice invoice)

    AccountInfo asInfo(Account account)
}
