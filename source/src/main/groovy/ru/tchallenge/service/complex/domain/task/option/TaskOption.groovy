package ru.tchallenge.service.complex.domain.task.option

import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import ru.tchallenge.service.complex.common.complementary.GenericComplementaryEntity
import ru.tchallenge.service.complex.domain.task.Task

@CompileStatic
@Entity
@Table(name = 'task_option')
class TaskOption extends GenericComplementaryEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'task_id', nullable = false, updatable = false)
    Task task

    @NotNull
    @Column(name = 'content', nullable = false)
    String content

    @NotNull
    @Min(0L)
    @Max(1L)
    @Column(name = 'correct', nullable = false)
    Integer correct

    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = 'textcode', nullable = false)
    String textcode
}
