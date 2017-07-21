package ru.tchallenge.service.complex.domain.task.status

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Table

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = 'task_status')
class TaskStatus extends GenericEnumeratedEntity {

}
