package ru.tchallenge.service.complex.domain.account

import java.util.stream.Collectors

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.common.search.SearchInvoice
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@ServiceComponent
class AccountService extends GenericService {

    @Autowired
    protected AccountMapper accountMapper

    @Autowired
    protected AccountRepository accountRepository

    AccountInfo create(AccountInvoice invoice) {
        throw new UnsupportedOperationException()
    }

    AccountInfo createAsClaim(AccountInvoice invoice) {
        throw new UnsupportedOperationException()
    }

    AccountInfo getById(String id) {
        def account = accountRepository.findById(id as Long)
        if (!account) {
            throw new RuntimeException("referenced account does not exist")
        }
        return accountMapper.asInfo(account)
    }

    SearchInfo<AccountInfo> search(SearchInvoice<AccountFilterInvoice> invoice) {
        def page = accountRepository.findPage(new PageRequest(0, 100))
        return new SearchInfo<AccountInfo>(
                items: page.content.stream().map({ Account account -> accountMapper.asInfo(account) }).collect(Collectors.toList()),
                offset: 0L,
                total: page.totalElements
        )
    }

    SearchInfo<AccountInfo> searchOnlyCandidates(SearchInvoice<AccountFilterInvoice> invoice) {
        def amendedInvoice = new SearchInvoice<AccountFilterInvoice>(
                filter: new AccountFilterInvoice(
                        emailPattern: invoice.filter.emailPattern,
                        loginPattern: invoice.filter.loginPattern,
                        personNamePattern: invoice.filter.personNamePattern,
                        realmTextcodes: ["CANDIDATE"],
                        statusTextcodes: invoice.filter.statusTextcodes
                ),
                limit: invoice.limit,
                offset: invoice.offset
        )
        return search(amendedInvoice)
    }

    AccountInfo update(AccountInvoice invoice) {
        throw new UnsupportedOperationException()
    }

    AccountInfo updateStatus(AccountInvoice invoice) {
        throw new UnsupportedOperationException()
    }
}
