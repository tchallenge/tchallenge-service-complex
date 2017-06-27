package ru.tchallenge.service.complex.domain.account

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice
import ru.tchallenge.service.complex.common.ordinal.sequence.OrdinalSequenceService
import ru.tchallenge.service.complex.common.search.SearchAware
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.utility.encryption.EncryptionService

@ServiceComponent
class AccountService extends GenericService implements SearchAware {

    @Autowired
    protected AccountMapper accountMapper

    @Autowired
    protected AccountPersister accountPersister

    @Autowired
    protected AccountRepository accountRepository

    @Autowired
    protected EncryptionService encryptionService

    @Autowired
    protected OrdinalSequenceService ordinalSequenceService

    AccountInfo create(AccountInvoice invoice) {
        def account = accountMapper.asEntity(invoice.with {
            status = initialStatusByRealm(invoice.realm)
            verification = verificationByRealm(invoice.realm)
            it
        })
        return saveAndInfo(account)
    }

    AccountInfo createAsClaim(AccountInvoice invoice) {
        def account = accountMapper.asEntity(invoice.with {
            status = initialStatusByRealm(invoice.realm)
            verification = verificationByRealm(invoice.realm)
            it
        })
        return saveAndInfo(account)
    }

    AccountInfo getById(String id) {
        return info(account(id))
    }

    SearchInfo<AccountInfo> search(AccountSearchInvoice invoice) {
        Page<Account> page = accountRepository.findPage(
                normalizePattern(invoice.filterEmailPattern),
                normalizePattern(invoice.filterLoginPattern),
                normalizePattern(invoice.filterPersonNamePattern),
                invoice.filterRealmTextcodes,
                invoice.filterStatusTextcodes,
                pageable(invoice)
        )
        return searchInfo(invoice, page) {
            Account account -> info(account)
        } as SearchInfo<AccountInfo>
    }

    SearchInfo<AccountInfo> searchOnlyCandidates(AccountSearchInvoice invoice) {
        return search(invoice.with {
            filterRealmTextcodes = ["CANDIDATE"]
            it
        })
    }

    AccountInfo update(AccountInvoice invoice) {
        throw new UnsupportedOperationException()
    }

    AccountInfo updateStatus(AccountInvoice invoice) {
        throw new UnsupportedOperationException()
    }

    private Account account(String id) {
        def result = accountRepository.findById(id as Long)
        if (!result) {
            throw new RuntimeException("referenced account does not exist")
        }
        return result
    }

    private AccountInfo info(Account account) {
        return accountMapper.asInfo(account)
    }

    private Account save(Account account) {
        return accountPersister.save(account)
    }

    private AccountInfo saveAndInfo(Account account) {
        return info(save(account))
    }

    private static EnumeratedInvoice initialStatusByRealm(EnumeratedInvoice realm) {
        return new EnumeratedInvoice(
                textcode: realm.textcode == "CANDIDATE" ? "APPROVED" : "CREATED"
        )
    }

    private static EnumeratedInvoice verificationByRealm(EnumeratedInvoice realm) {
        return new EnumeratedInvoice(
                textcode: realm.textcode == "ROBOT" ? "CERTIFICATE" : "PASSWORD"
        )
    }
}
