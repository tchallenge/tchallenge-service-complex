package ru.tchallenge.service.complex.domain.account.password

import groovy.transform.CompileStatic

import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

import ru.tchallenge.service.complex.common.complementary.GenericComplementaryEntity
import ru.tchallenge.service.complex.common.timestamp.TimestampedEntity
import ru.tchallenge.service.complex.domain.account.Account

@CompileStatic
@Entity
@Table(name = 'account_password')
class AccountPassword extends GenericComplementaryEntity implements TimestampedEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'account_id', nullable = false, updatable = false)
    Account account

    @NotNull
    @Column(name = 'active', nullable = false)
    Integer active

    @NotNull
    @Column(name = 'hash', nullable = false, updatable = false)
    String hash

    @NotNull
    @Column(name = 'expires_at', nullable = false, updatable = false)
    Instant expiresAt
}
