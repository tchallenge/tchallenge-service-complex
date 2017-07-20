package ru.tchallenge.service.complex.domain.account.realm

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@BootstrapComponent
class AccountRealmBootstrap extends GenericEnumeratedBootstrapBean<AccountRealm> {

    @Override
    protected Collection<AccountRealm> enumeratedEntities() {
        return [
                new AccountRealm(
                        textcode: "CANDIDATE",
                        title: "Кандидат/соискатель"
                ),
                new AccountRealm(
                        textcode: "EMPLOYEE",
                        title: "Сотрудник компании"
                ),
                new AccountRealm(
                        textcode: "ROBOT",
                        title: "Сторонний сервис"
                )
        ]
    }
}
