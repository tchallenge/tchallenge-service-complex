package ru.tchallenge.service.complex.domain.account

import java.util.stream.Collectors

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.common.search.GenericSearchInvoice
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

    SearchInfo<AccountInfo> search(AccountSearchInvoice invoice) {
        def page = accountRepository.findPage(
                invoice.filterEmailPattern,
                invoice.filterLoginPattern,
                invoice.filterPersonNamePattern,
                invoice.filterRealmTextcodes,
                invoice.filterStatusTextcodes,
                new PageRequest(invoice.pageOffset as Integer, invoice.pageSize as Integer)
        )
        return new SearchInfo<AccountInfo>(
                items: page.content.stream().map({ Account account -> accountMapper.asInfo(account) }).collect(Collectors.toList()),
                pageCount: page.totalPages as Long,
                pageOffset: invoice.pageOffset,
                pageSize: invoice.pageSize
        )
    }

    SearchInfo<AccountInfo> searchOnlyCandidates(AccountSearchInvoice invoice) {
        def amendedInvoice = new AccountSearchInvoice(
                filterEmailPattern: invoice.filterEmailPattern,
                filterLoginPattern: invoice.filterLoginPattern,
                filterPersonNamePattern: invoice.filterPersonNamePattern,
                filterRealmTextcodes: ["CANDIDATE"],
                filterStatusTextcodes: invoice.filterStatusTextcodes,
                pageOffset: invoice.pageOffset,
                pageSize: invoice.pageSize
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
