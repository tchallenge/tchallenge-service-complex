package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalEntity
import ru.tchallenge.service.complex.common.timestamp.TimestampedEntity
import ru.tchallenge.service.complex.domain.account.Account
import ru.tchallenge.service.complex.domain.assignment.Assignment
import ru.tchallenge.service.complex.domain.event.Event
import ru.tchallenge.service.complex.domain.maturity.Maturity
import ru.tchallenge.service.complex.domain.specialization.Specialization
import ru.tchallenge.service.complex.domain.workbook.status.WorkbookStatus

@CompileStatic
@Entity
@Table(name = 'workbook')
class Workbook extends GenericOrdinalEntity implements TimestampedEntity {

    @Column(name = 'score_actual')
    Integer scoreActual

    @Column(name = 'score_maximal')
    Integer scoreMaximal

    @OneToMany(mappedBy = 'workbook', cascade = CascadeType.ALL)
    Collection<Assignment> assignments = []

    @ManyToOne
    @JoinColumn(name = 'event_id')
    Event event

    @ManyToOne
    @JoinColumn(name = 'owner_id')
    Account owner

    @ManyToOne
    @JoinColumn(name = 'maturity_id')
    Maturity maturity

    @ManyToOne
    @JoinColumn(name = 'specialization_id')
    Specialization specialization

    @ManyToOne
    @JoinColumn(name = 'status_id')
    WorkbookStatus status
}
