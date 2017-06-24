package ru.tchallenge.service.complex.domain.robot

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.ordinal.GenericSecondaryEntity
import ru.tchallenge.service.complex.domain.account.Account

@CompileStatic
@Entity
@Table(name = "robot")
class Robot extends GenericSecondaryEntity {

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account
}
