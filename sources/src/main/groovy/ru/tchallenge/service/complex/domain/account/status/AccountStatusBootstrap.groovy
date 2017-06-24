package ru.tchallenge.service.complex.domain.account.status

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrap
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@BootstrapComponent
class AccountStatusBootstrap extends GenericEnumeratedBootstrap<AccountStatus> {

    @Override
    protected Collection<AccountStatus> enumeratedEntities() {
        return [
                new AccountStatus(
                        textcode: "CREATED",
                        title: "Создан"
                ),
                new AccountStatus(
                        textcode: "APPROVED",
                        title: "Подтвержден"
                ),
                new AccountStatus(
                        textcode: "SUSPENDED",
                        title: "Приостановлен"
                ),
                new AccountStatus(
                        textcode: "DELETED",
                        title: "Удален"
                )
        ]
    }
}
