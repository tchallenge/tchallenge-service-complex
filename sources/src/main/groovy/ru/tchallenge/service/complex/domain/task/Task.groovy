package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalEntity
import ru.tchallenge.service.complex.domain.task.category.TaskCategory
import ru.tchallenge.service.complex.domain.task.difficulty.TaskDifficulty
import ru.tchallenge.service.complex.domain.task.expectation.TaskExpectation
import ru.tchallenge.service.complex.domain.task.input.TaskInput
import ru.tchallenge.service.complex.domain.task.option.TaskOption
import ru.tchallenge.service.complex.domain.task.snippet.TaskSnippet
import ru.tchallenge.service.complex.domain.task.status.TaskStatus

@CompileStatic
@Entity
@Table(name = 'task')
class Task extends GenericOrdinalEntity {

    @Column(name = 'title')
    String title

    @Column(name = 'introduction')
    String introduction

    @Column(name = 'question')
    String question

    @OneToMany(mappedBy = 'task', cascade = CascadeType.ALL)
    Collection<TaskInput> inputs = []

    @OneToMany(mappedBy = 'task', cascade = CascadeType.ALL)
    Collection<TaskOption> options = []

    @OneToMany(mappedBy = 'task', cascade = CascadeType.ALL)
    Collection<TaskSnippet> snippets = []

    @ManyToMany
    @JoinTable(
            name = 'task_category_to_task_map',
            joinColumns = @JoinColumn(name = 'task_id'),
            inverseJoinColumns = @JoinColumn(name = 'task_category_id'))
    Collection<TaskCategory> categories = []

    @ManyToOne
    @JoinColumn(name = 'difficulty_id')
    TaskDifficulty difficulty

    @ManyToOne
    @JoinColumn(name = 'expectation_id')
    TaskExpectation expectation

    @ManyToOne
    @JoinColumn(name = 'status_id')
    TaskStatus status
}
