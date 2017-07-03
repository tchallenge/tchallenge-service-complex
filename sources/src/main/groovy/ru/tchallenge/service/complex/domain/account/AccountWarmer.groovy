package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericWarmer
import ru.tchallenge.service.complex.convention.component.WarmerComponent

@CompileStatic
@WarmerComponent
class AccountWarmer extends GenericWarmer {

    @Autowired
    protected AccountService accountService

    @Override
    void run() {
        accountService.allRealms
        accountService.allStatuses
        accountService.allVerifications
        accountService.allEmployeeRoles
        accountService.allRobotRoles
        accountService.search(new AccountSearchInvoice(
                pageNumber: 1,
                pageSize: 1000
        ))
    }
}
