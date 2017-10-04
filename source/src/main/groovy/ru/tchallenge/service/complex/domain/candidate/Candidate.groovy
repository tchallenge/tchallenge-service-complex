package ru.tchallenge.service.complex.domain.candidate

import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalEntity
import ru.tchallenge.service.complex.domain.account.Account

@CompileStatic
@Entity
@Table(name = 'candidate')
class Candidate extends GenericOrdinalEntity {

    @NotNull
    @OneToOne
    @JoinColumn(name = 'account_id', nullable = false, unique = true, updatable = false)
    Account account

    @Column(name = 'github')
    String github
}
