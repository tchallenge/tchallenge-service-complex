package ru.tchallenge.service.complex.domain.task.input

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.complementary.GenericComplementaryEntity

@CompileStatic
@Entity
@Table(name = "task_input")
class TaskInput extends GenericComplementaryEntity {

    @Column(name = "content")
    String content

    @Column(name = "regex")
    Integer regex

    @Column(name = "stance")
    Integer stance
}
