package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalEntity
import ru.tchallenge.service.complex.common.timestamp.TimestampedEntity
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
@Table(name = 'account')
class Account extends GenericOrdinalEntity implements TimestampedEntity {

    @NotNull
    @Column(name = 'email', nullable = false, unique = true)
    String email

    @NotNull
    @Column(name = 'login', nullable = false, unique = true)
    String login

    @OneToOne(mappedBy = 'account', cascade = CascadeType.ALL, orphanRemoval = true)
    Candidate candidate

    @OneToOne(mappedBy = 'account', cascade = CascadeType.ALL, orphanRemoval = true)
    Employee employee

    @OneToOne(mappedBy = 'account', cascade = CascadeType.ALL, orphanRemoval = true)
    Person person

    @OneToOne(mappedBy = 'account', cascade = CascadeType.ALL, orphanRemoval = true)
    Robot robot

    @OneToMany(mappedBy = 'account', cascade = CascadeType.ALL, orphanRemoval = true)
    Collection<AccountCertificate> certificates = []

    @OneToMany(mappedBy = 'account', cascade = CascadeType.ALL, orphanRemoval = true)
    Collection<AccountPassword> passwords = []

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'realm_id', nullable = false)
    AccountRealm realm

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'status_id', nullable = false)
    AccountStatus status

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'verification_id', nullable = false)
    AccountVerification verification
}
