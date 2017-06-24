package ru.tchallenge.service.complex.domain.account

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.ordinal.GenericPrimaryEntity
import ru.tchallenge.service.complex.domain.account.certificate.AccountCertificate
import ru.tchallenge.service.complex.domain.account.password.AccountPassword
import ru.tchallenge.service.complex.domain.account.realm.AccountRealm
import ru.tchallenge.service.complex.domain.account.status.AccountStatus
import ru.tchallenge.service.complex.domain.account.verification.AccountVerification
import ru.tchallenge.service.complex.domain.candidate.Candidate
import ru.tchallenge.service.complex.domain.employee.Employee
import ru.tchallenge.service.complex.domain.person.Person
import ru.tchallenge.service.complex.domain.robot.Robot

@CompileStatic
@Entity
@Table(name = "account")
class Account extends GenericPrimaryEntity {

    @Column(name = "email")
    String email

    @Column(name = "login")
    String login

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    Candidate candidate

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    Employee employee

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    Person person

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    Robot robot

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    Collection<AccountCertificate> certificates

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    Collection<AccountPassword> passwords

    @ManyToOne
    @JoinColumn(name = "realm_id")
    AccountRealm realm

    @ManyToOne
    @JoinColumn(name = "status_id")
    AccountStatus status

    @ManyToOne
    @JoinColumn(name = "verification_id")
    AccountVerification verification
}
