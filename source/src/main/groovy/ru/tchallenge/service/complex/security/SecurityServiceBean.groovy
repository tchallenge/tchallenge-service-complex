package ru.tchallenge.service.complex.security

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.domain.account.Account
import ru.tchallenge.service.complex.domain.account.AccountMapper
import ru.tchallenge.service.complex.domain.account.AccountRepository
import ru.tchallenge.service.complex.reliability.exception.NotSupportedException
import ru.tchallenge.service.complex.reliability.exception.SecurityViolationException
import ru.tchallenge.service.complex.security.shared.AccountViolationInfo
import ru.tchallenge.service.complex.security.authentication.AuthenticationInfo
import ru.tchallenge.service.complex.security.shared.PayloadService
import ru.tchallenge.service.complex.security.token.TokenInfo
import ru.tchallenge.service.complex.security.token.TokenService

@CompileStatic
@PackageScope
@ServiceComponent
class SecurityServiceBean extends GenericServiceBean implements SecurityService {

    @Autowired
    AccountRepository accountRepository

    @Autowired
    AccountMapper accountMapper

    @Autowired
    TokenService tokenService

    @Autowired
    PayloadService payloadService

    @Value('${tchallenge.security.token.predefined.employee.enabled}')
    Boolean predefinedTokenEmployeeEnabled

    @Value('${tchallenge.security.token.predefined.employee.login}')
    String predefinedTokenEmployeeLogin

    @Value('${tchallenge.security.token.predefined.employee.payload}')
    String predefinedTokenEmployeePayload

    @Override
    AuthenticationInfo authenticateByCertificate(String payload) {
        throw NotSupportedException.expectedSince(
                this,
                '1.3.x',
                'Authentication by certificate is not yet supported'
        )
    }

    @Override
    AuthenticationInfo authenticateByToken(String payload) {
        if (predefinedTokenEmployeeEnabled && payload == predefinedTokenEmployeePayload) {
            return bootstrappedEmployee()
        }
        def $token = tokenService.get(payload)
        def $accountId = payloadService.restoreAccountId($token.payload)
        def $account = accountRepository.findById($accountId as Long)
        createByAccountAndToken($account, $token)
    }

    private AuthenticationInfo bootstrappedEmployee() {
        def $now = now
        def $token = new TokenInfo(
                id: uuid,
                payload: predefinedTokenEmployeePayload,
                deactivationInMinutes: 0,
                createdAt: $now,
                expiresAt: $now,
                lastUsedAt: $now
        )
        def $account = accountRepository.findByLogin(predefinedTokenEmployeeLogin)
        createByAccountAndToken($account, $token)
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

    private static SecurityViolationException illegalStatus(Account account) {
        SecurityViolationException.of(this, AccountViolationInfo.illegalStatus(account))
    }

    private static SecurityViolationException unknownAccount() {
        SecurityViolationException.of(this, AccountViolationInfo.unknown())
    }
}
