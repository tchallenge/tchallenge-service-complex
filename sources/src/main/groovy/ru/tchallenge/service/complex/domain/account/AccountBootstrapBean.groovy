package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent
import ru.tchallenge.service.complex.domain.account.certificate.AccountCertificate
import ru.tchallenge.service.complex.domain.account.password.AccountPassword
import ru.tchallenge.service.complex.domain.account.realm.AccountRealm
import ru.tchallenge.service.complex.domain.account.realm.AccountRealmRepository
import ru.tchallenge.service.complex.domain.account.status.AccountStatus
import ru.tchallenge.service.complex.domain.account.status.AccountStatusRepository
import ru.tchallenge.service.complex.domain.account.verification.AccountVerification
import ru.tchallenge.service.complex.domain.account.verification.AccountVerificationRepository
import ru.tchallenge.service.complex.domain.candidate.Candidate
import ru.tchallenge.service.complex.domain.employee.Employee
import ru.tchallenge.service.complex.domain.employee.role.EmployeeRole
import ru.tchallenge.service.complex.domain.employee.role.EmployeeRoleRepository
import ru.tchallenge.service.complex.domain.person.Person
import ru.tchallenge.service.complex.domain.robot.Robot
import ru.tchallenge.service.complex.domain.robot.role.RobotRole
import ru.tchallenge.service.complex.domain.robot.role.RobotRoleRepository
import ru.tchallenge.service.complex.utility.encryption.EncryptionService

@CompileStatic
@BootstrapComponent
class AccountBootstrapBean extends GenericOrdinalBootstrapBean<Account> implements AccountBootstrap {

    // TODO: consider breaking this bootstrap into several

    @Autowired
    AccountRealmRepository accountRealmRepository

    @Autowired
    AccountStatusRepository accountStatusRepository

    @Autowired
    AccountVerificationRepository accountVerificationRepository

    @Autowired
    EmployeeRoleRepository employeeRoleRepository

    @Autowired
    RobotRoleRepository robotRoleRepository

    @Autowired
    EncryptionService encryptionService

    @Override
    protected Collection<Account> entities() {
        return [
                candidateKuznetcov(),
                candidateChistyakov(),
                employeeIvanov(),
                employeePetrov(),
                employeeSidorov(),
                robotSystemA()
        ]
    }

    private Account candidateKuznetcov() {
        new Account(
                email: 'alex.kuznetcov@somemail.net',
                login: 'alexalex',
                candidate: new Candidate(),
                person: new Person(
                        quickname: 'Алекс'
                ),
                passwords: [
                        password('bootstrapped.password.inactive.1', false),
                        password('bootstrapped.password')
                ],
                realm: realm('CANDIDATE'),
                status: status('APPROVED'),
                verification: verification('PASSWORD')
        )
    }

    private Account candidateChistyakov() {
        new Account(
                email: 'andrew1995@somemail.net',
                login: 'andre',
                candidate: new Candidate(),
                person: new Person(
                        firstname: 'Андрей',
                        lastname: 'Чистяков',
                        quickname: 'Andrew'
                ),
                passwords: [
                        password('bootstrapped.password')
                ],
                realm: realm('CANDIDATE'),
                status: status('APPROVED'),
                verification: verification('PASSWORD')
        )
    }

    private Account employeeIvanov() {
        new Account(
                email: 'sergei.ivanov@somemail.net',
                login: 'serge',
                employee: new Employee(
                        roles: employeeRoles('CANDMOD', 'CANDVIEW')
                ),
                person: new Person(
                        firstname: 'Сергей',
                        lastname: 'Иванов',
                        quickname: 'Сержо'
                ),
                passwords: [
                        password('bootstrapped.password.inactive.1', false),
                        password('bootstrapped.password')
                ],
                realm: realm('EMPLOYEE'),
                status: status('APPROVED'),
                verification: verification('PASSWORD')
        )
    }

    private Account employeePetrov() {
        new Account(
                email: 'ivan.petrov@anothermail.net',
                login: 'ipetrov',
                employee: new Employee(
                        roles: employeeRoles('ADMIN')
                ),
                person: new Person(
                        firstname: 'Иван',
                        lastname: 'Петров',
                        quickname: 'Vano'
                ),
                passwords: [
                        password('bootstrapped.password')
                ],
                realm: realm('EMPLOYEE'),
                status: status('APPROVED'),
                verification: verification('PASSWORD')
        )
    }

    private Account employeeSidorov() {
        new Account(
                email: 'egor.sidorov@anothermail.net',
                login: 'e.sid',
                employee: new Employee(
                        roles: employeeRoles('USERMOD')
                ),
                person: new Person(
                        firstname: 'Егор',
                        lastname: 'Сидоров',
                        quickname: 'Сид'
                ),
                passwords: [
                        password('bootstrapped.password')
                ],
                realm: realm('EMPLOYEE'),
                status: status('SUSPENDED'),
                verification: verification('PASSWORD')
        )
    }

    private Account robotSystemA() {
        new Account(
                email: 'system.a.feedback@anothermail.net',
                login: 'system.a',
                robot: new Robot(
                        roles: robotRoles('USERMOD')
                ),
                certificates: [
                        certificate('bootstrapped.certificate')
                ],
                realm: realm('ROBOT'),
                status: status('APPROVED'),
                verification: verification('CERTIFICATE')
        )
    }

    private AccountCertificate certificate(String payload) {
        certificate(payload, true)
    }

    private AccountCertificate certificate(String payload, boolean active) {
        new AccountCertificate(
                active: foundamentals.flag(active),
                hash: hash(payload)
        )
    }

    private AccountPassword password(String textvalue) {
        password(textvalue, true)
    }

    private AccountPassword password(String textvalue, boolean active) {
        new AccountPassword(
                active: foundamentals.flag(active),
                hash: hash(textvalue)
        )
    }

    private AccountRealm realm(String textcode) {
        enumerateds.one(accountRealmRepository, textcode)
    }

    private Collection<EmployeeRole> employeeRoles(String... textcodes) {
        enumerateds.some(employeeRoleRepository, textcodes)
    }

    private Collection<RobotRole> robotRoles(String... textcodes) {
        enumerateds.some(robotRoleRepository, textcodes)
    }

    private AccountStatus status(String textcode) {
        enumerateds.one(accountStatusRepository, textcode)
    }

    private AccountVerification verification(String textcode) {
        enumerateds.one(accountVerificationRepository, textcode)
    }

    private String hash(String input) {
        encryptionService.hash(input)
    }
}
