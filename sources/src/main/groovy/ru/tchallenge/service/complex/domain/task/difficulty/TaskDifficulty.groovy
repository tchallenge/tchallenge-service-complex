package ru.tchallenge.service.complex.domain.task.difficulty

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Table

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = 'task_difficulty')
class TaskDifficulty extends GenericEnumeratedEntity {

}
