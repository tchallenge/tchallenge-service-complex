package ru.tchallenge.service.complex.domain.account

import groovy.transform.TypeChecked

import javax.validation.Valid

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice
import ru.tchallenge.service.complex.domain.candidate.CandidateInvoice
import ru.tchallenge.service.complex.domain.employee.EmployeeInvoice
import ru.tchallenge.service.complex.domain.person.PersonInvoice
import ru.tchallenge.service.complex.domain.robot.RobotInvoice
import ru.tchallenge.service.complex.validation.constraints.AccountEmail
import ru.tchallenge.service.complex.validation.constraints.OrdinalId
import ru.tchallenge.service.complex.validation.constraints.Required
import ru.tchallenge.service.complex.validation.groups.Create
import ru.tchallenge.service.complex.validation.groups.Edit
import ru.tchallenge.service.complex.validation.groups.Identify
import ru.tchallenge.service.complex.validation.groups.Status

@TypeChecked
class AccountInvoice {

    @OrdinalId(groups = Identify)
    String id

    @AccountEmail(groups = [Create, Edit])
    String email

    @AccountEmail(groups = [Create, Edit])
    String login

    @Valid
    CandidateInvoice candidate

    @Valid
    EmployeeInvoice employee

    @Valid
    PersonInvoice person

    @Valid
    RobotInvoice robot

    @Valid
    @AccountEmail(groups = [Create, Edit])
    EnumeratedInvoice realm

    @Valid
    @Required(groups = Status)
    EnumeratedInvoice status
}
