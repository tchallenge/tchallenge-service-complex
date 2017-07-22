package ru.tchallenge.service.complex.domain.event

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

import groovy.transform.CompileStatic

import javax.validation.constraints.NotNull

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalEntity
import ru.tchallenge.service.complex.common.timestamp.TimestampedEntity
import ru.tchallenge.service.complex.domain.event.category.EventCategory
import ru.tchallenge.service.complex.domain.event.interval.EventInterval
import ru.tchallenge.service.complex.domain.event.status.EventStatus

@CompileStatic
@Entity
@Table(name = 'event')
class Event extends GenericOrdinalEntity implements TimestampedEntity {

    @NotNull
    @Column(name = 'textcode', nullable = false, unique = true)
    String textcode

    @NotNull
    @Column(name = 'title')
    String title

    @Column(name = 'subtitle')
    String subtitle

    @Column(name = 'description')
    String description

    @NotNull
    @Column(name = 'greeting', nullable = false)
    String greeting

    @OneToMany(mappedBy = 'event', cascade = CascadeType.ALL)
    Collection<EventInterval> intervals = []

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'category_id', nullable = false)
    EventCategory category

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'status_id', nullable = false)
    EventStatus status
}
