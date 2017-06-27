package ru.tchallenge.service.complex.domain.event.category

import javax.persistence.Entity
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = "event_category")
class EventCategory extends GenericEnumeratedEntity {

}
