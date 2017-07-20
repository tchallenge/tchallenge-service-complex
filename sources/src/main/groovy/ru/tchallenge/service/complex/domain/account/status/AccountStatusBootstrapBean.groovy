package ru.tchallenge.service.complex.domain.account.status

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@PackageScope
@BootstrapComponent
class AccountStatusBootstrapBean extends GenericEnumeratedBootstrapBean<AccountStatus> {

    @Override
    protected Collection<AccountStatus> enumeratedEntities() {
        [
                new AccountStatus(
                        textcode: 'CREATED',
                        title: 'Создан'
                ),
                new AccountStatus(
                        textcode: 'APPROVED',
                        title: 'Подтвержден'
                ),
                new AccountStatus(
                        textcode: 'SUSPENDED',
                        title: 'Приостановлен'
                ),
                new AccountStatus(
                        textcode: 'DELETED',
                        title: 'Удален'
                )
        ]
    }
}
