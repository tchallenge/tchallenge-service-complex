package ru.tchallenge.service.complex.domain.assignment

import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

import ru.tchallenge.service.complex.common.complementary.GenericComplementaryEntity
import ru.tchallenge.service.complex.domain.assignment.status.AssignmentStatus
import ru.tchallenge.service.complex.domain.task.Task
import ru.tchallenge.service.complex.domain.workbook.Workbook

@CompileStatic
@Entity
@Table(name = 'assignment')
class Assignment extends GenericComplementaryEntity {

    @Column(name = 'input')
    String input

    @Min(0L)
    @Column(name = 'score_actual')
    Integer scoreActual

    @NotNull
    @Min(0L)
    @Column(name = 'score_maximal', nullable = false, updatable = false)
    Integer scoreMaximal

    @NotNull
    @Min(1L)
    @Column(name = 'stance', nullable = false, updatable = false)
    Integer stance

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'task_id', nullable = false, updatable = false)
    Task task

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'workbook_id', nullable = false, updatable = false)
    Workbook workbook

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'status_id', nullable = false)
    AssignmentStatus status
}
