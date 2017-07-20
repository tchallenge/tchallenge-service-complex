package ru.tchallenge.service.complex.security.rescue

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericFacadeBean
import ru.tchallenge.service.complex.convention.component.FacadeComponent
import ru.tchallenge.service.complex.domain.account.Account
import ru.tchallenge.service.complex.domain.account.AccountRepository
import ru.tchallenge.service.complex.reliability.exception.SecurityViolationException
import ru.tchallenge.service.complex.reliability.exception.ViolationException
import ru.tchallenge.service.complex.reliability.violation.BaseViolationInfo
import ru.tchallenge.service.complex.reliability.violation.ViolationInfo
import ru.tchallenge.service.complex.security.voucher.VoucherInfo
import ru.tchallenge.service.complex.security.voucher.VoucherService

@CompileStatic
@PackageScope
@FacadeComponent
class RescueFacadeBean extends GenericFacadeBean implements RescueFacade {

    @Autowired
    AccountRepository accountRepository

    @Autowired
    VoucherService voucherService

    @Override
    void create(RescueInvoice invoice) {
        anyViolation(invoice).ifPresent { throw violationException(it as ViolationInfo) }
        def $account = retrieveLegalAccountIfPossible(invoice).orElseThrow { throw noLegalAccount() }
        def $voucher = voucherService.create($account.id as String)
        sendByEmail(invoice, $voucher, $account)
    }

    private Optional<Account> retrieveLegalAccountIfPossible(RescueInvoice invoice) {
        def $email = invoice.email
        def $login = invoice.login
        Account $account = $email ? accountRepository.findByEmail($email) : accountRepository.findByLogin($login)
        Optional.ofNullable(isLegalForRescue($account) ? $account : null)
    }

    private static boolean isLegalForRescue(Account account) {
        account && account.status.textcode != 'APPROVED' && account.verification.textcode != 'CERTIFICATE'
    }

    private static Optional<ViolationInfo> anyViolation(RescueInvoice invoice) {
        // TODO: move into a specific validator
        // TODO: implement real validation
        Optional.empty()
    }

    private static void sendByEmail(RescueInvoice invoice, VoucherInfo voucher, Account account) {
        def $email = account.email
        def $voucherPayload = voucher.payload
        def $url = invoice.url
        // TODO: implement this method
        throw new UnsupportedOperationException('Sending rescue by email is not implemented yet')
    }

    private static ViolationException violationException(ViolationInfo violation) {
        new ViolationException(this.class, violation)
    }

    private static SecurityViolationException noLegalAccount() {
        new SecurityViolationException(this.class, new BaseViolationInfo(description: 'no legal account'))
    }
}
