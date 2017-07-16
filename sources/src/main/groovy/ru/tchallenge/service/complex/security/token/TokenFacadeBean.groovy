package ru.tchallenge.service.complex.security.token

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacade
import ru.tchallenge.service.complex.convention.component.FacadeComponent
import ru.tchallenge.service.complex.domain.account.Account
import ru.tchallenge.service.complex.domain.account.AccountMapper
import ru.tchallenge.service.complex.domain.account.AccountRepository
import ru.tchallenge.service.complex.reliability.exception.SecurityViolationException
import ru.tchallenge.service.complex.security.shared.AccountViolationInfo
import ru.tchallenge.service.complex.security.voucher.VoucherService
import ru.tchallenge.service.complex.utility.encryption.EncryptionService

@CompileStatic
@PackageScope
@FacadeComponent
class TokenFacadeBean extends GenericFacade implements TokenFacade {

    @Autowired
    AccountRepository accountRepository

    @Autowired
    AccountMapper accountMapper

    @Autowired
    EncryptionService encryptionService

    @Autowired
    TokenService tokenService

    @Autowired
    VoucherService voucherService

    @Override
    TokenInfo create(TokenInvoice invoice) {
        TokenInfo $result
        if (invoice.voucher) {
            $result = fromVoucher(invoice.voucher)
        } else {
            $result = fromLoginAndPassword(invoice.login, invoice.password)
        }
        $result
    }

    @Override
    TokenInfo get() {
        token
    }

    @Override
    void remove() {
        tokenService.remove(token.payload)
    }

    @Override
    void removeAllForAccount() {
        tokenService.removeAllForAccount(token.payload)
    }

    private TokenInfo getToken() {
        authenticationContext
                .authentication
                .orElseThrow { unauthorized() }
                .token
    }

    private TokenInfo fromLoginAndPassword(String login, String password) {
        def $account = accountRepository.findByLogin(login)
        if (!$account) {
            throw illegalCredentials(login)
        }
        if ($account.verification.textcode != "PASSWORD") {
            throw illegalCredentials(login)
        }
        def $accountPassword = $account.passwords.last()
        if (!$accountPassword || !$accountPassword.active || !verifyHash(password, $accountPassword.hash)) {
            throw illegalCredentials(login)
        }
        tokenService.create($account.id as String)
    }

    private boolean verifyHash(String password, String hash) {
        encryptionService.verify(password, hash)
    }

    private TokenInfo fromVoucher(String voucherPayload) {
        // TODO: implement this method
        throw new UnsupportedOperationException('Creation of token from a given voucher is not yet implemented')
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
