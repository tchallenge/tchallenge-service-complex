package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.domain.account.realm.AccountRealmRepository
import ru.tchallenge.service.complex.domain.account.status.AccountStatusRepository
import ru.tchallenge.service.complex.domain.account.verification.AccountVerificationRepository
import ru.tchallenge.service.complex.domain.employee.role.EmployeeRoleRepository
import ru.tchallenge.service.complex.domain.robot.role.RobotRoleRepository
import ru.tchallenge.service.complex.utility.encryption.EncryptionService
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.all
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.one
import static ru.tchallenge.service.complex.common.search.SearchTransformations.info
import static ru.tchallenge.service.complex.common.search.SearchTransformations.normalizePattern
import static ru.tchallenge.service.complex.common.search.SearchTransformations.pageable

@CompileStatic
@ServiceComponent
class AccountService extends GenericService {

    @Autowired
    protected AccountMapper accountMapper

    @Autowired
    protected AccountPersister accountPersister

    @Autowired
    protected AccountRepository accountRepository

    @Autowired
    protected AccountRealmRepository accountRealmRepository

    @Autowired
    protected AccountStatusRepository accountStatusRepository

    @Autowired
    protected AccountVerificationRepository accountVerificationRepository

    @Autowired
    protected EmployeeRoleRepository employeeRoleRepository

    @Autowired
    protected RobotRoleRepository robotRoleRepository

    @Autowired
    protected EncryptionService encryptionService

    AccountInfo create(AccountInvoice invoice) {
        def account = accountMapper.asEntity(invoice.with {
            id = null
            status = initialStatusByRealm(invoice.realm)
            verification = verificationByRealm(invoice.realm)
            it
        })
        return saveAndInfo(account)
    }

    AccountInfo createAsClaim(AccountInvoice invoice) {
        def account = accountMapper.asEntity(invoice.with {
            id = null
            employee?.roles = []
            robot?.roles = []
            status = initialStatusByRealm(invoice.realm)
            verification = verificationByRealm(invoice.realm)
            it
        })
        return saveAndInfo(account)
    }

    Collection<EnumeratedInfo> getAllRealms() {
        return all(accountRealmRepository)
    }

    Collection<EnumeratedInfo> getAllStatuses() {
        return all(accountStatusRepository)
    }

    Collection<EnumeratedInfo> getAllVerifications() {
        return all(accountVerificationRepository)
    }

    Collection<EnumeratedInfo> getAllEmployeeRoles() {
        return all(employeeRoleRepository)
    }

    Collection<EnumeratedInfo> getAllRobotRoles() {
        return all(robotRoleRepository)
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
        return info(invoice, page) {
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
        def account = account(invoice.id)
        def trimmedInvoice = invoice.with {
            id = null
            employee?.roles = null
            realm = null
            status = null
            verification = null
            it
        }
        def mergedAccount = accountMapper.asEntity(account, trimmedInvoice)
        return saveAndInfo(mergedAccount)
    }

    AccountInfo updateStatus(AccountInvoice invoice) {
        def account = account(invoice.id).with {
            status = one(accountStatusRepository, invoice.status)
            it
        }
        return saveAndInfo(account)
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
