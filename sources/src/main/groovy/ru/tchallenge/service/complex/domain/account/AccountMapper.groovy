package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericMapperBean
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.*
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
@MapperComponent
class AccountMapper extends GenericMapperBean {

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
            candidate = invoice.candidate ? candidateMapper.asEntity(candidate, invoice.candidate) : candidate
            employee = invoice.employee ? employeeMapper.asEntity(employee, invoice.employee) : employee
            person = invoice.person ? personMapper.asEntity(person, invoice.person) : person
            robot = invoice.robot ? robotMapper.asEntity(robot, invoice.robot) : robot
            realm = invoice.realm ? one(accountRealmRepository, invoice.realm) : realm
            status = invoice.status ? one(accountStatusRepository, invoice.status) : status
            verification = invoice.verification ? one(accountVerificationRepository, invoice.verification) : verification
            it
        }
    }

    AccountInfo asInfo(Account account) {
        return new AccountInfo(
                id: account.id as String,
                email: account.email,
                login: account.login,
                candidate: candidateInfo(account.candidate),
                employee: employeeInfo(account.employee),
                person: personInfo(account.person),
                robot: robotInfo(account.robot),
                realm: info(account.realm),
                status: info(account.status),
                verification: info(account.verification),
                createdAt: account.createdAt,
                lastModifiedAt: account.lastModifiedAt
        )
    }

    private CandidateInfo candidateInfo(Candidate candidate) {
        return candidate ? candidateMapper.asInfo(candidate) : null
    }

    private EmployeeInfo employeeInfo(Employee employee) {
        return employee ? employeeMapper.asInfo(employee) : null
    }

    private PersonInfo personInfo(Person person) {
        return person ? personMapper.asInfo(person) : null
    }

    private RobotInfo robotInfo(Robot robot) {
        return robot ? robotMapper.asInfo(robot) : null
    }
}
