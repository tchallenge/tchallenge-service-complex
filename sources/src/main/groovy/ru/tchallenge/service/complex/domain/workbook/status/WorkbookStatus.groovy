package ru.tchallenge.service.complex.domain.workbook.status

import javax.persistence.Entity
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = "workbook_status")
class WorkbookStatus extends GenericEnumeratedEntity {

}
