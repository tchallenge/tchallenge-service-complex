package ru.tchallenge.service.complex.domain.account.realm

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrap
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@BootstrapComponent
class AccountRealmBootstrap extends GenericEnumeratedBootstrap<AccountRealm> {

    @Override
    protected Collection<AccountRealm> enumeratedEntities() {
        return []
    }
}
