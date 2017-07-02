package ru.tchallenge.service.complex.domain.assignment

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.complementary.GenericComplementaryEntity
import ru.tchallenge.service.complex.domain.assignment.status.AssignmentStatus
import ru.tchallenge.service.complex.domain.task.Task
import ru.tchallenge.service.complex.domain.workbook.Workbook

@CompileStatic
@Entity
@Table(name = "assignment")
class Assignment extends GenericComplementaryEntity {

    @Column(name = "input")
    String input

    @Column(name = "score_actual")
    Integer scoreActual

    @Column(name = "score_maximal")
    Integer scoreMaximal

    @Column(name = "stance")
    Integer stance

    @ManyToOne
    @JoinColumn(name = "task_id")
    Task task

    @ManyToOne
    @JoinColumn(name = "workbook_id")
    Workbook workbook

    @ManyToOne
    @JoinColumn(name = "status_id")
    AssignmentStatus status
}
