package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

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

    @Min(0L)
    @Column(name = 'score_actual')
    Integer scoreActual

    @NotNull
    @Min(0L)
    @Column(name = 'score_maximal', nullable = false, updatable = false)
    Integer scoreMaximal

    @OneToMany(mappedBy = 'workbook', cascade = CascadeType.ALL)
    Collection<Assignment> assignments = []

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'event_id', nullable = false, updatable = false)
    Event event

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'owner_id', nullable = false, updatable = false)
    Account owner

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'maturity_id', nullable = false, updatable = false)
    Maturity maturity

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'specialization_id', nullable = false, updatable = false)
    Specialization specialization

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'status_id', nullable = false)
    WorkbookStatus status
}
