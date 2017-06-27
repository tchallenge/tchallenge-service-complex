package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericMapper
import ru.tchallenge.service.complex.common.enumerated.EnumeratedHelper
import ru.tchallenge.service.complex.common.ordinal.sequence.OrdinalSequenceService
import ru.tchallenge.service.complex.convention.component.MapperComponent
import ru.tchallenge.service.complex.domain.account.realm.AccountRealmRepository
import ru.tchallenge.service.complex.domain.account.status.AccountStatusRepository
import ru.tchallenge.service.complex.domain.account.verification.AccountVerificationRepository
import ru.tchallenge.service.complex.domain.candidate.Candidate
import ru.tchallenge.service.complex.domain.candidate.CandidateInvoice
import ru.tchallenge.service.complex.domain.candidate.CandidateMapper
import ru.tchallenge.service.complex.domain.employee.EmployeeMapper
import ru.tchallenge.service.complex.domain.employee.role.EmployeeRoleRepository
import ru.tchallenge.service.complex.domain.person.PersonMapper
import ru.tchallenge.service.complex.domain.robot.RobotMapper
import ru.tchallenge.service.complex.utility.encryption.EncryptionService

@CompileStatic
@MapperComponent
class AccountMapper extends GenericMapper {

    @Autowired
    protected CandidateMapper candidateMapper

    @Autowired
    protected EmployeeMapper employeeMapper

    @Autowired
    protected PersonMapper personMapper

    @Autowired
    protected RobotMapper robotMapper

    @Autowired
    protected AccountRealmRepository accountRealmRepository

    @Autowired
    protected AccountStatusRepository accountStatusRepository

    @Autowired
    protected AccountVerificationRepository accountVerificationRepository

    Account asEntity(AccountInvoice invoice) {
        return asEntity(null, invoice)
    }

    Account asEntity(Account entity, AccountInvoice invoice) {
        entity = entity ?: new Account()
        return entity.with {
            id = invoice.id as Long ?: id
            email = invoice.email ?: email
            login = invoice.login ?: login
            candidate = invoice.candidate ? candidateMapper.merge(candidate, invoice.candidate) : candidate
            employee = invoice.employee ? employeeMapper.merge(employee, invoice.employee) : employee
            person = invoice.person ? personMapper.merge(person, invoice.person) : person
            robot = invoice.robot ? robotMapper.merge(robot, invoice.robot) : robot
            realm = invoice.realm ? EnumeratedHelper.one(invoice.realm, accountRealmRepository) : realm
            status = invoice.status ? EnumeratedHelper.one(invoice.status, accountStatusRepository) : status
            verification = invoice.verification ? EnumeratedHelper.one(invoice.verification, accountVerificationRepository) : verification
            it
        }
    }

    AccountInfo asInfo(Account account) {
        return new AccountInfo(
                id: account.id as String,
                email: account.email,
                login: account.login,
                candidate: account.candidate ? candidateMapper.asInfo(account.candidate) : null,
                employee: account.employee ? employeeMapper.asInfo(account.employee) : null,
                person: account.person ? personMapper.asInfo(account.person) : null,
                robot: account.robot ? robotMapper.asInfo(account.robot) : null,
                realm: EnumeratedHelper.one(account.realm),
                status: EnumeratedHelper.one(account.status),
                verification: EnumeratedHelper.one(account.verification),
                createdAt: account.createdAt,
                lastModifiedAt: account.lastModifiedAt
        )
    }
}
