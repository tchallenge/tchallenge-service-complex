package ru.tchallenge.service.complex.domain.task.option

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.complementary.GenericComplementaryEntity

@CompileStatic
@Entity
@Table(name = "task_option")
class TaskOption extends GenericComplementaryEntity {

    @Column(name = "content")
    String content

    @Column(name = "correct")
    Integer correct

    @Column(name = "textcode")
    String textcode
}
