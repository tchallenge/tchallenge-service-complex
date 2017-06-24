package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.enumerated.EnumeratedHelper
import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalBootstrap
import ru.tchallenge.service.complex.common.ordinal.sequence.OrdinalSequenceBootstrap
import ru.tchallenge.service.complex.common.ordinal.sequence.OrdinalSequenceService
import ru.tchallenge.service.complex.convention.component.BootstrapComponent
import ru.tchallenge.service.complex.domain.account.realm.AccountRealmBootstrap
import ru.tchallenge.service.complex.domain.account.realm.AccountRealmRepository
import ru.tchallenge.service.complex.domain.account.status.AccountStatusBootstrap
import ru.tchallenge.service.complex.domain.account.status.AccountStatusRepository
import ru.tchallenge.service.complex.domain.account.verification.AccountVerificationBootstrap
import ru.tchallenge.service.complex.domain.account.verification.AccountVerificationRepository
import ru.tchallenge.service.complex.domain.employee.Employee
import ru.tchallenge.service.complex.domain.employee.role.EmployeeRoleBootstrap
import ru.tchallenge.service.complex.domain.employee.role.EmployeeRoleRepository
import ru.tchallenge.service.complex.domain.person.Person
import ru.tchallenge.service.complex.domain.robot.role.RobotRoleBootstrap

@CompileStatic
@BootstrapComponent
class AccountBootstrap extends GenericOrdinalBootstrap<Account> {

    @Autowired
    protected OrdinalSequenceBootstrap ordinalSequenceBootstrap

    @Autowired
    protected AccountRealmBootstrap accountRealmBootstrap

    @Autowired
    protected AccountStatusBootstrap accountStatusBootstrap

    @Autowired
    protected AccountVerificationBootstrap accountVerificationBootstrap

    @Autowired
    protected EmployeeRoleBootstrap employeeRoleBootstrap

    @Autowired
    protected RobotRoleBootstrap robotRoleBootstrap

    @Autowired
    protected AccountRealmRepository accountRealmRepository

    @Autowired
    protected AccountStatusRepository accountStatusRepository

    @Autowired
    protected AccountVerificationRepository accountVerificationRepository

    @Autowired
    protected EmployeeRoleRepository employeeRoleRepository

    @Autowired
    protected OrdinalSequenceService ordinalSequenceService

    @Override
    protected Collection<Account> entities() {
        return [
                sidorov()
        ]
    }

    private Account sidorov() {
        def id = nextId()
        return new Account(
                id: id,
                email: "ivan.sidorov@somemail.net",
                login: "ivan.sidorov",
                employee: new Employee(
                        id: id,
                        roles: EnumeratedHelper.many(employeeRoleRepository, "ADMIN")
                ),
                person: new Person(
                        id: id,
                        firstname: "Иван",
                        lastname: "Сидоров",
                        quickname: "Vano"
                ),
                realm: accountRealmRepository.findById("EMPLOYEE"),
                status: accountStatusRepository.findById("APPROVED"),
                verification: accountVerificationRepository.findById("PASSWORD")
        )
    }

    private Long nextId() {
        return ordinalSequenceService.nextValue("domain.workbook")
    }
}
