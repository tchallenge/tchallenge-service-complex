package ru.tchallenge.service.complex.domain.event.status

import javax.persistence.Entity
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = "event_status")
class EventStatus extends GenericEnumeratedEntity {

}
