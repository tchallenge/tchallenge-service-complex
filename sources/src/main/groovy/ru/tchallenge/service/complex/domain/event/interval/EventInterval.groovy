package ru.tchallenge.service.complex.domain.event.interval

import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.complementary.GenericComplementaryEntity

@CompileStatic
@Entity
@Table(name = "event_interval")
class EventInterval extends GenericComplementaryEntity {

    @Column
    Instant since

    @Column
    Instant until
}
