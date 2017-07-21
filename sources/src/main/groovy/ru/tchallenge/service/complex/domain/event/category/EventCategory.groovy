package ru.tchallenge.service.complex.domain.event.category

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Table

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = 'event_category')
class EventCategory extends GenericEnumeratedEntity {

}
