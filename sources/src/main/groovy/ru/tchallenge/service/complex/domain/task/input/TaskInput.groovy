package ru.tchallenge.service.complex.domain.task.input

import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

import ru.tchallenge.service.complex.common.complementary.GenericComplementaryEntity
import ru.tchallenge.service.complex.domain.task.Task

@CompileStatic
@Entity
@Table(name = 'task_input')
class TaskInput extends GenericComplementaryEntity {

    @ManyToOne
    @JoinColumn(name = 'task_id')
    Task task

    @Column(name = 'content')
    String content

    @Column(name = 'regex')
    Integer regex

    @Column(name = 'stance')
    Integer stance
}
