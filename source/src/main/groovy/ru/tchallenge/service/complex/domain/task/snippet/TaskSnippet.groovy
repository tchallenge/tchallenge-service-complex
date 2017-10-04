package ru.tchallenge.service.complex.domain.task.snippet

import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

import ru.tchallenge.service.complex.common.complementary.GenericComplementaryEntity
import ru.tchallenge.service.complex.domain.task.Task
import ru.tchallenge.service.complex.domain.task.snippet.style.TaskSnippetStyle

@CompileStatic
@Entity
@Table(name = 'task_snippet')
class TaskSnippet extends GenericComplementaryEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'task_id', nullable = false, updatable = false)
    Task task

    @NotNull
    @Column(name = 'content', nullable = false)
    String content

    @NotNull
    @Min(1L)
    @Column(name = 'stance', nullable = false)
    Integer stance

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'style_id', nullable = false)
    TaskSnippetStyle style
}
