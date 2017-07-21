package ru.tchallenge.service.complex.domain.task.category

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Table

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = 'task_category')
class TaskCategory extends GenericEnumeratedEntity {

    // TODO: this entity must NOT be Enumerated
}
