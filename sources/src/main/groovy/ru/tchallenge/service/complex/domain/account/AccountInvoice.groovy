package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice
import ru.tchallenge.service.complex.domain.candidate.CandidateInvoice
import ru.tchallenge.service.complex.domain.employee.EmployeeInvoice
import ru.tchallenge.service.complex.domain.person.PersonInvoice
import ru.tchallenge.service.complex.domain.robot.RobotInvoice

@CompileStatic
class AccountInvoice {

    String id
    String email
    String login
    CandidateInvoice candidate
    EmployeeInvoice employee
    PersonInvoice person
    RobotInvoice robot
    EnumeratedInvoice realm
    EnumeratedInvoice status
    EnumeratedInvoice verification
}
