package ru.tchallenge.service.complex.domain.event.status

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Table

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = 'event_status')
class EventStatus extends GenericEnumeratedEntity {

}
