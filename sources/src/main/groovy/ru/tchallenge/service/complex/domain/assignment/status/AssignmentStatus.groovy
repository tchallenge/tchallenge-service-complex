package ru.tchallenge.service.complex.domain.assignment.status

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Table

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = 'assignment_status')
class AssignmentStatus extends GenericEnumeratedEntity {

}
