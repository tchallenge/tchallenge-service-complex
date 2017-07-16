package ru.tchallenge.service.complex.security.authentication

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.domain.account.Account
import ru.tchallenge.service.complex.domain.account.AccountMapper
import ru.tchallenge.service.complex.domain.account.AccountRepository
import ru.tchallenge.service.complex.reliability.exception.SecurityViolationException
import ru.tchallenge.service.complex.security.token.TokenInfo
import ru.tchallenge.service.complex.security.token.TokenService
import ru.tchallenge.service.complex.utility.encryption.EncryptionService

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

    AuthenticationInfo createFromLoginAndPassword(String login, String password) {
        def account = accountRepository.findByLogin(login)
        if (!account) {
            throw illegalCredentials(login)
        }
        if (account.verification.textcode != "PASSWORD") {
            throw illegalCredentials(login)
        }
        def accountPassword = account.passwords.last()
        if (!accountPassword || !accountPassword.active || !encryptionService.verify(password, accountPassword.hash)) {
            throw illegalCredentials(login)
        }
        def token = tokenService.create(account.id as String)
        createByAccountAndToken(account, token)
    }

    private AuthenticationInfo createByAccountAndToken(Account account, TokenInfo token) {
        if (!account) {
            throw unknownAccount()
        }
        if (account.status.textcode != "APPROVED") {
            throw illegalStatus(account)
        }
        new AuthenticationInfo(
                account: accountMapper.asInfo(account),
                token: token
        )
    }

    private static SecurityViolationException illegalCredentials(String login) {
        SecurityViolationException.of(this, AccountViolationInfo.illegalCredentials(login))
    }

    private static SecurityViolationException illegalStatus(Account account) {
        SecurityViolationException.of(this, AccountViolationInfo.illegalStatus(account))
    }

    private static SecurityViolationException unknownAccount() {
        SecurityViolationException.of(this, AccountViolationInfo.unknown())
    }
}
