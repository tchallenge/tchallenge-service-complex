package ru.tchallenge.service.complex.domain.task.snippet

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.complementary.GenericComplementaryEntity
import ru.tchallenge.service.complex.domain.task.snippet.style.TaskSnippetStyle

@CompileStatic
@Entity
@Table(name = "task_snippet")
class TaskSnippet extends GenericComplementaryEntity {

    @Column(name = "content")
    String content

    @Column(name = "stance")
    Integer stance

    @ManyToOne
    @JoinColumn(name = "style_id")
    TaskSnippetStyle style
}
