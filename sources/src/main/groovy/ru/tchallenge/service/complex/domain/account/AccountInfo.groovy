package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.timestamp.TimestampedValue
import ru.tchallenge.service.complex.domain.candidate.CandidateInfo
import ru.tchallenge.service.complex.domain.employee.EmployeeInfo
import ru.tchallenge.service.complex.domain.person.PersonInfo
import ru.tchallenge.service.complex.domain.robot.RobotInfo

@CompileStatic
class AccountInfo implements TimestampedValue {

    String id
    String email
    String login
    CandidateInfo candidate
    EmployeeInfo employee
    PersonInfo person
    RobotInfo robot
    EnumeratedInfo realm
    EnumeratedInfo status
    EnumeratedInfo verification
}
