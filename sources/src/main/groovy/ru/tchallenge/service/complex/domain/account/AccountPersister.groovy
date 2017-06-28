package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalPersister
import ru.tchallenge.service.complex.convention.component.PersisterComponent
import ru.tchallenge.service.complex.domain.account.certificate.AccountCertificate
import ru.tchallenge.service.complex.domain.account.password.AccountPassword

@CompileStatic
@PersisterComponent
class AccountPersister extends GenericOrdinalPersister<Account> {

    @Override
    protected void prepare(Account entity) {
        if (!entity.id) {
            entity.id = ordinalSequenceService.nextValue("domain.account")
        }
        if (entity.candidate) {
            entity.candidate.id = entity.id
            entity.candidate.account = entity
        }
        if (entity.certificates) {
            entity.certificates.forEach { AccountCertificate it -> it.account = entity }
        }
        if (entity.employee) {
            entity.employee.id = entity.id
            entity.employee.account = entity
        }
        if (entity.person) {
            entity.person.id = entity.id
            entity.person.account = entity
        }
        if (entity.passwords) {
            entity.passwords.forEach { AccountPassword it -> it.account = entity }
        }
        if (entity.robot) {
            entity.robot.id = entity.id
            entity.robot.account = entity
        }
    }
}
