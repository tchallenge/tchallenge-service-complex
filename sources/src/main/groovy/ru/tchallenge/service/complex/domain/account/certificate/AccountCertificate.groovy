package ru.tchallenge.service.complex.domain.account.certificate

import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.complementary.GenericComplementaryEntity
import ru.tchallenge.service.complex.common.timestamp.TimestampedEntity
import ru.tchallenge.service.complex.domain.account.Account

@CompileStatic
@Entity
@Table(name = "account_certificate")
class AccountCertificate extends GenericComplementaryEntity implements TimestampedEntity {

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account

    @Column
    Integer active

    @Column
    String hash

    @Column
    Instant expiresAt
}
