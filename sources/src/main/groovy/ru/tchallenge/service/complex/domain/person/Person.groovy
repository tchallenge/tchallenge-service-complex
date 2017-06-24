package ru.tchallenge.service.complex.domain.person

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.ordinal.GenericSecondaryEntity
import ru.tchallenge.service.complex.domain.account.Account

@CompileStatic
@Entity
@Table(name = "person")
class Person extends GenericSecondaryEntity {

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account

    @Column(name = "firstname")
    String firstname

    @Column(name = "lastname")
    String lastname

    @Column(name = "quickname")
    String quickname
}
