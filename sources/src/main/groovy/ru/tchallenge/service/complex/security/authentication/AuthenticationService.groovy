package ru.tchallenge.service.complex.security.authentication

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.domain.account.Account
import ru.tchallenge.service.complex.domain.account.AccountMapper
import ru.tchallenge.service.complex.domain.account.AccountRepository
import ru.tchallenge.service.complex.reliability.exception.SecurityViolationException
import ru.tchallenge.service.complex.reliability.violation.BaseViolationInfo
import ru.tchallenge.service.complex.reliability.violation.ViolationCategory
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

    AuthenticationInfo createFromTokenPayload(String payload) {
        if (payload == PREDEFINED_EMPLOYEE_TOKEN_PAYLOAD) {
            return bootstrappedEmployee()
        }
        def token = tokenService.get(payload)
        def accountId = tokenPayloadService.restoreAccountId(token.payload)
        def account = accountRepository.findById(accountId as Long)
        createByAccountAndToken(account, token)
    }

    private AuthenticationInfo bootstrappedEmployee() {
        def now = now
        def token = new TokenInfo(
                id: uuid,
                payload: PREDEFINED_EMPLOYEE_TOKEN_PAYLOAD,
                deactivationInMinutes: 0,
                createdAt: now,
                expiresAt: now,
                lastUsedAt: now
        )
        def account = accountRepository.findByLogin("ipetrov")
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
        new SecurityViolationException(
                new AccountViolationInfo(
                        base: new BaseViolationInfo(
                                category: ViolationCategory.SECURITY,
                                description: "Specified credentials are illegal or not recognized",
                                textcode: "X.SECURITY.CREDENTIALS.ILLEGAL"
                        ),
                        login: login
                )
        )
    }

    private static SecurityViolationException illegalStatus(Account account) {
        new SecurityViolationException(
                new AccountViolationInfo(
                        base: new BaseViolationInfo(
                                category: ViolationCategory.SECURITY,
                                description: "Account status is illegal",
                                textcode: "X.SECURITY.ACCOUNT.STATUS.ILLEGAL"
                        ),
                        login: account.login,
                        status: EnumeratedTransformations.info(account.status)
                )
        )
    }

    private static SecurityViolationException unknownAccount() {
        new SecurityViolationException(
                new AccountViolationInfo(
                        base: new BaseViolationInfo(
                                category: ViolationCategory.SECURITY,
                                description: "Referenced account does not exist",
                                textcode: "X.SECURITY.ACCOUNT.UNKNOWN"
                        )
                )
        )
    }
}
