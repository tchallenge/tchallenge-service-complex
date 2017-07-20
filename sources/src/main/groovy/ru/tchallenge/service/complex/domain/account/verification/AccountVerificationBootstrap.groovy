package ru.tchallenge.service.complex.domain.account.verification

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@BootstrapComponent
class AccountVerificationBootstrap extends GenericEnumeratedBootstrapBean<AccountVerification> {

    @Override
    protected Collection<AccountVerification> enumeratedEntities() {
        return [
                new AccountVerification(
                        textcode: "CERTIFICATE",
                        title: "Постоянный сертификат"
                ),
                new AccountVerification(
                        textcode: "PASSWORD",
                        title: "Секретный пароль"
                )
        ]
    }
}
