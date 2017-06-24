package ru.tchallenge.service.complex.domain.candidate

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalEntity
import ru.tchallenge.service.complex.domain.account.Account

@CompileStatic
@Entity
@Table(name = "candidate")
class Candidate extends GenericOrdinalEntity {

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account

    @Column(name = "github")
    String github
}
