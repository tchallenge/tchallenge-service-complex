package ru.tchallenge.service.complex.domain.event.interval

import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.complementary.GenericComplementaryEntity
import ru.tchallenge.service.complex.domain.event.Event

@CompileStatic
@Entity
@Table(name = "event_interval")
class EventInterval extends GenericComplementaryEntity {

    @ManyToOne
    @JoinColumn(name = "event_id")
    Event event

    @Column
    Instant since

    @Column
    Instant until
}
