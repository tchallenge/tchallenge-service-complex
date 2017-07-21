package ru.tchallenge.service.complex.domain.event

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalEntity
import ru.tchallenge.service.complex.common.timestamp.TimestampedEntity
import ru.tchallenge.service.complex.domain.event.category.EventCategory
import ru.tchallenge.service.complex.domain.event.interval.EventInterval
import ru.tchallenge.service.complex.domain.event.status.EventStatus

@CompileStatic
@Entity
@Table(name = 'event')
class Event extends GenericOrdinalEntity implements TimestampedEntity {

    @Column(name = 'textcode')
    String textcode

    @Column(name = 'title')
    String title

    @Column(name = 'subtitle')
    String subtitle

    @Column(name = 'description')
    String description

    @Column(name = 'greeting')
    String greeting

    @OneToMany(mappedBy = 'event', cascade = CascadeType.ALL)
    Collection<EventInterval> intervals = []

    @ManyToOne
    @JoinColumn(name = 'category_id')
    EventCategory category

    @ManyToOne
    @JoinColumn(name = 'status_id')
    EventStatus status
}
