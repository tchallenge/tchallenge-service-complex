package ru.tchallenge.service.complex.domain.account.password

import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.complementary.GenericComplementaryEntity

@CompileStatic
@Entity
@Table(name = "account_password")
class AccountPassword extends GenericComplementaryEntity {

    @Column
    Integer active

    @Column
    String hash

    @Column
    private Instant createdAt

    @Column
    private Instant expiresAt

    Instant getCreatedAt() {
        return createdAt
    }

    Instant getExpiresAt() {
        return expiresAt
    }

    @Override
    protected void onInsert() {
        createdAt = Instant.now()
    }
}
