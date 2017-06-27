package ru.tchallenge.service.complex.security.authentication

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.domain.account.Account
import ru.tchallenge.service.complex.domain.account.AccountMapper
import ru.tchallenge.service.complex.domain.account.AccountRepository
import ru.tchallenge.service.complex.security.token.TokenInfo
import ru.tchallenge.service.complex.security.token.TokenPayloadService
import ru.tchallenge.service.complex.security.token.TokenService
import ru.tchallenge.service.complex.utility.encryption.EncryptionService

@CompileStatic
@ServiceComponent
class AuthenticationService extends GenericService {

    private static final String PREDEFINED_EMPLOYEE_TOKEN_PAYLOAD = "PREDEFINED_EMPLOYEE_TOKEN_PAYLOAD"

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
        def token = tokenService.create(account.id as String)
        return createByAccountAndToken(account, token)
    }

    AuthenticationInfo createFromTokenPayload(String payload) {
        if (payload == PREDEFINED_EMPLOYEE_TOKEN_PAYLOAD) {
            return bootstrappedEmployee()
        }
        def token = tokenService.get(payload)
        def accountId = tokenPayloadService.restoreAccountId(token.payload)
        def account = accountRepository.findById(accountId as Long)
        return createByAccountAndToken(account, token)
    }

    private AuthenticationInfo bootstrappedEmployee() {
        def now = now()
        def token = new TokenInfo(
                id: uuid(),
                payload: PREDEFINED_EMPLOYEE_TOKEN_PAYLOAD,
                deactivationInMinutes: 0,
                createdAt: now,
                expiresAt: now,
                lastUsedAt: now
        )
        def account = accountRepository.findByLogin("ipetrov")
        return createByAccountAndToken(account, token)
    }

    private AuthenticationInfo createByAccountAndToken(Account account, TokenInfo token) {
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
