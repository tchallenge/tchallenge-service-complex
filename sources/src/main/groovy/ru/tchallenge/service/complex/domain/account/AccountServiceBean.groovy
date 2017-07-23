package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.domain.account.status.AccountStatusRepository
import ru.tchallenge.service.complex.utility.encryption.EncryptionService

@CompileStatic
@PackageScope
@ServiceComponent
class AccountServiceBean extends GenericServiceBean implements AccountService {

    @Autowired
    AccountMapper accountMapper

    @Autowired
    AccountPersister accountPersister

    @Autowired
    AccountRepository accountRepository

    @Autowired
    AccountStatusRepository accountStatusRepository

    @Autowired
    EncryptionService encryptionService

    AccountInfo create(AccountInvoice invoice) {
        def $account = accountMapper.asEntity(invoice.with {
            id = null
            status = initialStatusByRealm(invoice.realm)
            verification = verificationByRealm(invoice.realm)
            it
        })
        saveAndInfo($account)
    }

    AccountInfo createAsClaim(AccountInvoice invoice) {
        def $account = accountMapper.asEntity(invoice.with {
            id = null
            employee?.roles = []
            robot?.roles = []
            status = initialStatusByRealm(invoice.realm)
            verification = verificationByRealm(invoice.realm)
            it
        })
        saveAndInfo($account)
    }

    AccountInfo getById(String id) {
        info(account(id))
    }

    SearchInfo<AccountInfo> search(AccountSearchInvoice invoice) {
        Page<Account> $page = accountRepository.findPage(
                searches.normalizePattern(invoice.filterEmailPattern),
                searches.normalizePattern(invoice.filterLoginPattern),
                searches.normalizePattern(invoice.filterPersonNamePattern),
                invoice.filterRealmTextcodes,
                invoice.filterStatusTextcodes,
                searches.pageable(invoice)
        )
        searches.info(invoice, $page) { Account account -> info(account) }
    }

    SearchInfo<AccountInfo> searchOnlyCandidates(AccountSearchInvoice invoice) {
        search(invoice.with {
            filterRealmTextcodes = ['CANDIDATE']
            it
        })
    }

    AccountInfo update(AccountInvoice invoice) {
        def $account = account(invoice.id)
        def $trimmedInvoice = invoice.with {
            id = null
            employee?.roles = null
            realm = null
            status = null
            verification = null
            it
        }
        def $mergedAccount = accountMapper.asEntity($account, $trimmedInvoice)
        saveAndInfo($mergedAccount)
    }

    AccountInfo updateStatus(AccountInvoice invoice) {
        def $account = account(invoice.id).with {
            status = enumerateds.one(accountStatusRepository, invoice.status)
            it
        }
        saveAndInfo($account)
    }

    private Account account(String id) {
        def $result = accountRepository.findById(id as Long)
        if (!$result) {
            throw new RuntimeException('referenced account does not exist')
        }
        $result
    }

    private AccountInfo info(Account account) {
        accountMapper.asInfo(account)
    }

    private Account save(Account account) {
        accountPersister.save(account)
    }

    private AccountInfo saveAndInfo(Account account) {
        info(save(account))
    }

    private static EnumeratedInvoice initialStatusByRealm(EnumeratedInvoice realm) {
        new EnumeratedInvoice(
                textcode: realm.textcode == 'CANDIDATE' ? 'APPROVED' : 'CREATED'
        )
    }

    private static EnumeratedInvoice verificationByRealm(EnumeratedInvoice realm) {
        new EnumeratedInvoice(
                textcode: realm.textcode == 'ROBOT' ? 'CERTIFICATE' : 'PASSWORD'
        )
    }
}
