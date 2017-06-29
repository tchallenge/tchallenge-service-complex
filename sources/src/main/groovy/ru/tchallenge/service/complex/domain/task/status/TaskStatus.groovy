package ru.tchallenge.service.complex.domain.task.status

import javax.persistence.Entity
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = "task_status")
class TaskStatus extends GenericEnumeratedEntity {

}
