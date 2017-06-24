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

    SearchInfo<AccountInfo> search(SearchInvoice<AccountInvoice> invoice) {
        def page = accountRepository.findPage(new PageRequest(0, 100))
        return new SearchInfo<AccountInfo>(
                items: page.content.stream().map({ Account account -> accountMapper.asInfo(account) }).collect(Collectors.toList()),
                offset: 0L,
                total: page.totalElements
        )
    }
}
