package ru.tchallenge.service.complex.domain.account.verification

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@PackageScope
@BootstrapComponent
class AccountVerificationBootstrapBean extends GenericEnumeratedBootstrapBean<AccountVerification> {

    @Override
    protected Collection<AccountVerification> enumeratedEntities() {
        [
                new AccountVerification(
                        textcode: 'CERTIFICATE',
                        title: 'Постоянный сертификат'
                ),
                new AccountVerification(
                        textcode: 'PASSWORD',
                        title: 'Секретный пароль'
                )
        ]
    }
}
