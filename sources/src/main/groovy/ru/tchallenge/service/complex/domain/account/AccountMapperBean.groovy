package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent
import ru.tchallenge.service.complex.domain.account.realm.AccountRealmRepository
import ru.tchallenge.service.complex.domain.account.status.AccountStatusRepository
import ru.tchallenge.service.complex.domain.account.verification.AccountVerificationRepository
import ru.tchallenge.service.complex.domain.candidate.Candidate
import ru.tchallenge.service.complex.domain.candidate.CandidateInfo
import ru.tchallenge.service.complex.domain.candidate.CandidateMapper
import ru.tchallenge.service.complex.domain.employee.Employee
import ru.tchallenge.service.complex.domain.employee.EmployeeInfo
import ru.tchallenge.service.complex.domain.employee.EmployeeMapper
import ru.tchallenge.service.complex.domain.person.Person
import ru.tchallenge.service.complex.domain.person.PersonInfo
import ru.tchallenge.service.complex.domain.person.PersonMapper
import ru.tchallenge.service.complex.domain.robot.Robot
import ru.tchallenge.service.complex.domain.robot.RobotInfo
import ru.tchallenge.service.complex.domain.robot.RobotMapper

@CompileStatic
@PackageScope
@MapperComponent
class AccountMapperBean extends GenericMapperBean implements AccountMapper {

    @Autowired
    CandidateMapper candidateMapper

    @Autowired
    EmployeeMapper employeeMapper

    @Autowired
    PersonMapper personMapper

    @Autowired
    RobotMapper robotMapper

    @Autowired
    AccountRealmRepository accountRealmRepository

    @Autowired
    AccountStatusRepository accountStatusRepository

    @Autowired
    AccountVerificationRepository accountVerificationRepository

    @Override
    Account asEntity(AccountInvoice invoice) {
        asEntity(null, invoice)
    }

    @Override
    Account asEntity(Account entity, AccountInvoice invoice) {
        entity = entity ?: new Account()
        entity.with {
            id = invoice.id as Long ?: id
            email = invoice.email ?: email
            login = invoice.login ?: login
            candidate = invoice.candidate ? candidateMapper.asEntity(candidate, invoice.candidate) : candidate
            employee = invoice.employee ? employeeMapper.asEntity(employee, invoice.employee) : employee
            person = invoice.person ? personMapper.asEntity(person, invoice.person) : person
            robot = invoice.robot ? robotMapper.asEntity(robot, invoice.robot) : robot
            realm = invoice.realm ? enumerateds.one(accountRealmRepository, invoice.realm) : realm
            status = invoice.status ? enumerateds.one(accountStatusRepository, invoice.status) : status
            verification = invoice.verification ? enumerateds.one(accountVerificationRepository, invoice.verification) : verification
            it
        }
    }

    @Override
    AccountInfo asInfo(Account account) {
        new AccountInfo(
                id: account.id as String,
                email: account.email,
                login: account.login,
                candidate: candidateInfo(account.candidate),
                employee: employeeInfo(account.employee),
                person: personInfo(account.person),
                robot: robotInfo(account.robot),
                realm: enumerateds.info(account.realm),
                status: enumerateds.info(account.status),
                verification: enumerateds.info(account.verification),
                createdAt: account.createdAt,
                lastModifiedAt: account.lastModifiedAt
        )
    }

    private CandidateInfo candidateInfo(Candidate candidate) {
        candidate ? candidateMapper.asInfo(candidate) : null
    }

    private EmployeeInfo employeeInfo(Employee employee) {
        employee ? employeeMapper.asInfo(employee) : null
    }

    private PersonInfo personInfo(Person person) {
        person ? personMapper.asInfo(person) : null
    }

    private RobotInfo robotInfo(Robot robot) {
        robot ? robotMapper.asInfo(robot) : null
    }
}
