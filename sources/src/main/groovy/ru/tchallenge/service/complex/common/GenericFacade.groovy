package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.domain.account.AccountInfo
import ru.tchallenge.service.complex.security.authentication.AuthenticationContext

@CompileStatic
abstract class GenericFacade extends GenericComponent {

    @Autowired
    protected AuthenticationContext authenticationContext

    protected AccountInfo authenticated() {
        def result = authenticationContext.authentication.account
        if (!result) {
            throw unauthorized()
        }
        return result
    }

    protected AccountInfo authenticatedCandidate() {
        def result = authenticated()
        if (!result.candidate) {
            throw unauthorized()
        }
        return result
    }

    protected boolean authenticatedEmployee(String... roles) {
        def result = authenticated()
        def employee = result.employee
        if (!employee) {
            return false
        }
        if (!roles || !roles.size()) {
            return true
        }
        for (EnumeratedInfo employeeRole : employee.roles) {
            def textcode = employeeRole.textcode
            if (textcode == "ADMIN" || roles.contains(textcode)) {
                return true
            }
        }
        return false
    }

    protected RuntimeException unauthorized() {
        throw new RuntimeException("unauthorized")
    }
}
