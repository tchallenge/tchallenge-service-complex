package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericMapper
import ru.tchallenge.service.complex.common.enumerated.EnumeratedHelper
import ru.tchallenge.service.complex.convention.component.MapperComponent
import ru.tchallenge.service.complex.domain.candidate.CandidateMapper
import ru.tchallenge.service.complex.domain.employee.EmployeeMapper
import ru.tchallenge.service.complex.domain.person.PersonMapper
import ru.tchallenge.service.complex.domain.robot.RobotMapper

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
