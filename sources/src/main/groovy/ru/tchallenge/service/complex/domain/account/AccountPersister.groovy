package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.common.ordinal.sequence.OrdinalSequenceService
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.domain.account.certificate.AccountCertificate
import ru.tchallenge.service.complex.domain.account.password.AccountPassword

@CompileStatic
@ServiceComponent
class AccountPersister extends GenericService {

    @Autowired
    protected OrdinalSequenceService ordinalSequenceService

    @Autowired
    protected AccountRepository accountRepository

    Account save(Account account) {
        if (!account.id) {
            account.id = ordinalSequenceService.nextValue("domain.account")
        }
        if (account.candidate) {
            account.candidate.id = account.id
            account.candidate.account = account
        }
        if (account.certificates) {
            account.certificates.forEach { AccountCertificate it -> it.account = account }
        }
        if (account.employee) {
            account.employee.id = account.id
            account.employee.account = account
        }
        if (account.person) {
            account.person.id = account.id
            account.person.account = account
        }
        if (account.passwords) {
            account.passwords.forEach { AccountPassword it -> it.account = account }
        }
        if (account.robot) {
            account.robot.id = account.id
            account.robot.account = account
        }
        accountRepository.save(account)
        return account
    }
}
