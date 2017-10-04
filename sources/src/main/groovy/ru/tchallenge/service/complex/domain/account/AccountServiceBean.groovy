package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.domain.account.realm.AccountRealmRepository
import ru.tchallenge.service.complex.domain.account.status.AccountStatusRepository
import ru.tchallenge.service.complex.domain.account.verification.AccountVerificationRepository
import ru.tchallenge.service.complex.domain.candidate.Candidate
import ru.tchallenge.service.complex.domain.employee.Employee
import ru.tchallenge.service.complex.domain.person.Person
import ru.tchallenge.service.complex.domain.robot.Robot
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
    AccountRealmRepository accountRealmRepository

    @Autowired
    AccountStatusRepository accountStatusRepository

    @Autowired
    AccountVerificationRepository accountVerificationRepository

    @Autowired
    EncryptionService encryptionService

    @Override
    AccountInfo create(AccountInvoice invoice) {
        Account account = new Account()
        ensureUniqueness(account, invoice)
        mergeOnCreate(account, invoice)
        saveAndInfo(account)
    }

    private void mergeOnCreate(Account account, AccountInvoice invoice) {
        account.with {
            it.email = invoice.email
            it.login = invoice.login
            it.candidate = invoice.candidate ? new Candidate() : null
            it.employee = invoice.employee ? new Employee() : null
            it.person = invoice.person ? new Person() : null
            it.robot = invoice.robot ? new Robot() : null
            it.passwords = []
            it.certificates = []
            it.realm = enumerateds.one(accountRealmRepository, invoice.realm)
            it.status = enumerateds.one(accountStatusRepository, initialStatusByRealm(invoice.realm))
            it.verification = enumerateds.one(accountVerificationRepository, verificationByRealm(invoice.realm))
        }
    }

    private void mergeOnUpdate(Account account, AccountInvoice invoice) {
        account.with {
            it.email = invoice.email
            it.login = invoice.login
            it.candidate = null // invoice.candidate
            it.employee = null // invoice.employee
            it.person = null // invoice.person
            it.robot = null // invoice.robot
            it.passwords = []
            it.certificates = []
            it.realm = enumerateds.one(accountRealmRepository, invoice.realm)
            it.status = enumerateds.one(accountStatusRepository, initialStatusByRealm(invoice.realm))
            it.verification = enumerateds.one(accountVerificationRepository, verificationByRealm(invoice.realm))
        }
    }

    private void mergeOnStatus(Account account, AccountInvoice invoice) {
        account.with {
            it.status = enumerateds.one(accountStatusRepository, initialStatusByRealm(invoice.realm))
        }
    }

    private void ensureUniqueness(Account account, AccountInvoice invoice) {
        Account foundByEmail = accountRepository.findByEmail(invoice.email)
        if (foundByEmail && foundByEmail.id != account.id) {
            throw new RuntimeException('account already exists')
        }
        Account foundByLogin = accountRepository.findByLogin(invoice.login)
        if (foundByLogin && foundByLogin.id != account.id) {
            throw new RuntimeException('account already exists')
        }
    }

    private AccountInfo mergeAndInfo(Account account, AccountInvoice invoice) {
        // TODO: merge invoice into account
        saveAndInfo(account)
    }

    @Override
    AccountInfo createAsClaim(AccountInvoice invoice) {
        /*
        def $account = accountMapper.asEntity(invoice.with {
            id = null
            employee?.roles = []
            robot?.roles = []
            status = initialStatusByRealm(invoice.realm)
            verification = verificationByRealm(invoice.realm)
            it
        })
        */
        Account account = null
        saveAndInfo(account)
    }

    @Override
    AccountInfo getById(String id) {
        info(account(id))
    }

    @Override
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

    @Override
    SearchInfo<AccountInfo> searchOnlyCandidates(AccountSearchInvoice invoice) {
        search(invoice.with {
            filterRealmTextcodes = ['CANDIDATE']
            it
        })
    }

    @Override
    AccountInfo update(AccountInvoice invoice) {
        /*
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
        */
        Account account = null
        saveAndInfo(account)
    }

    @Override
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
