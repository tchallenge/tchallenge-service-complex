package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalBootstrap
import ru.tchallenge.service.complex.convention.component.BootstrapComponent
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
import ru.tchallenge.service.complex.utility.encryption.EncryptionService
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.one
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.some
import static ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals.flag

@CompileStatic
@BootstrapComponent
class AccountBootstrap extends GenericOrdinalBootstrap<Account> {

    @Autowired
    protected AccountRealmRepository accountRealmRepository

    @Autowired
    protected AccountStatusRepository accountStatusRepository

    @Autowired
    protected AccountVerificationRepository accountVerificationRepository

    @Autowired
    protected EmployeeRoleRepository employeeRoleRepository

    @Autowired
    protected EncryptionService encryptionService

    @Override
    protected Collection<Account> entities() {
        return [
                employeeIvanov(),
                employeePetrov(),
                employeeSidorov(),
                candidateKuznetcov()
        ]
    }

    private Account employeeIvanov() {
        return new Account(
                email: "sergei.ivanov@somemail.net",
                login: "serge",
                employee: new Employee(
                        roles: roles("CANDMOD", "CANDVIEW")
                ),
                person: new Person(
                        firstname: "Сергей",
                        lastname: "Иванов",
                        quickname: "Сержо"
                ),
                passwords: [
                        activePassword("text")
                ],
                realm: realm("EMPLOYEE"),
                status: status("APPROVED"),
                verification: verification("PASSWORD")
        )
    }

    private Account employeePetrov() {
        return new Account(
                email: "ivan.petrov@anothermail.net",
                login: "ipetrov",
                employee: new Employee(
                        roles: roles("ADMIN")
                ),
                person: new Person(
                        firstname: "Иван",
                        lastname: "Петров",
                        quickname: "Vano"
                ),
                passwords: [
                        activePassword("test")
                ],
                realm: realm("EMPLOYEE"),
                status: status("APPROVED"),
                verification: verification("PASSWORD")
        )
    }

    private Account employeeSidorov() {
        return new Account(
                email: "egor.sidorov@anothermail.net",
                login: "e.sid",
                employee: new Employee(
                        roles: roles("USERMOD")
                ),
                person: new Person(
                        firstname: "Егор",
                        lastname: "Сидоров",
                        quickname: "Сид"
                ),
                passwords: [
                        activePassword("test")
                ],
                realm: realm("EMPLOYEE"),
                status: status("APPROVED"),
                verification: verification("PASSWORD")
        )
    }

    private Account candidateKuznetcov() {
        return new Account(
                email: "alex.kuznetcov@somemail.net",
                login: "alexalex",
                candidate: new Candidate(),
                person: new Person(
                        quickname: "Алекс"
                ),
                passwords: [
                        activePassword("test")
                ],
                realm: realm("EMPLOYEE"),
                status: status("APPROVED"),
                verification: verification("PASSWORD")
        )
    }

    private AccountPassword activePassword(String password) {
        return new AccountPassword(
                active: flag(true),
                hash: passwordHash(password)
        )
    }

    private String passwordHash(String password) {
        return encryptionService.hash(password)
    }

    private AccountRealm realm(String textcode) {
        return one(accountRealmRepository, textcode)
    }

    private Collection<EmployeeRole> roles(String... textcodes) {
        return some(employeeRoleRepository, textcodes)
    }

    private AccountStatus status(String textcode) {
        return one(accountStatusRepository, textcode)
    }

    private AccountVerification verification(String textcode) {
        return one(accountVerificationRepository, textcode)
    }
}
