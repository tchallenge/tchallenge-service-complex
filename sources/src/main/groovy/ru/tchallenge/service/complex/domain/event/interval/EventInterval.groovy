package ru.tchallenge.service.complex.domain.event.interval

import groovy.transform.CompileStatic

import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

import ru.tchallenge.service.complex.common.complementary.GenericComplementaryEntity
import ru.tchallenge.service.complex.domain.event.Event

@CompileStatic
@Entity
@Table(name = 'event_interval')
class EventInterval extends GenericComplementaryEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'event_id', nullable = false, updatable = false)
    Event event

    @NotNull
    @Column(name = 'since', nullable = false)
    Instant since

    @NotNull
    @Column(name = 'until', nullable = false)
    Instant until
}
