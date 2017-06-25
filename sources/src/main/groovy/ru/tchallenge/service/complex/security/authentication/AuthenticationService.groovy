package ru.tchallenge.service.complex.security.authentication

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.domain.account.AccountMapper
import ru.tchallenge.service.complex.domain.account.AccountRepository
import ru.tchallenge.service.complex.security.token.TokenPayloadService
import ru.tchallenge.service.complex.security.token.TokenService
import ru.tchallenge.service.complex.utility.serialization.EncryptionService

@CompileStatic
@ServiceComponent
class AuthenticationService extends GenericService {

    @Autowired
    protected AccountRepository accountRepository

    @Autowired
    protected AccountMapper accountMapper

    @Autowired
    protected EncryptionService encryptionService

    @Autowired
    protected TokenService tokenService

    @Autowired
    protected TokenPayloadService tokenPayloadService

    AuthenticationInfo createFromLoginAndPassword(String login, String password) {
        def account = accountRepository.findByLogin(login)
        if (!account) {
            throw unknownCredentials()
        }
        if (account.verification.textcode != "PASSWORD") {
            throw unknownCredentials()
        }
        def passwordHash = encryptionService.passwordHash(password)
        def accountPassword = account.passwords.last()
        if (!accountPassword || !accountPassword.active || accountPassword.hash != passwordHash) {
            throw unknownCredentials()
        }
        if (account.status.textcode != "APPROVED") {
            throw illegalStatus(account.status.textcode)
        }
        def token = tokenService.create(account.id as String)
        return new AuthenticationInfo(
                account: accountMapper.asInfo(account),
                token: token
        )
    }

    AuthenticationInfo createFromTokenPayload(String payload) {
        def token = tokenService.get(payload)
        def accountId = tokenPayloadService.restoreAccountId(token.payload)
        def account = accountRepository.findById(accountId as Long)
        if (!account) {
            throw unknownAccount()
        }
        if (account.status.textcode != "APPROVED") {
            throw illegalStatus(account.status.textcode)
        }
        return new AuthenticationInfo(
                account: accountMapper.asInfo(account),
                token: token
        )
    }

    private static RuntimeException illegalStatus(String status) {
        return new RuntimeException("authentication failed: account status is " + status)
    }

    private static RuntimeException unknownAccount() {
        return new RuntimeException("authentication failed: account does not exist")
    }

    private static RuntimeException unknownCredentials() {
        return new RuntimeException("authentication failed: login or password are incorrect")
    }
}
