package ru.tchallenge.service.complex.domain.workbook

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "workbooks")
class WorkbookEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    Long id
}
