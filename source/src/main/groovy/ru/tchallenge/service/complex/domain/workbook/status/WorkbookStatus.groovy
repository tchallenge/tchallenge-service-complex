package ru.tchallenge.service.complex.domain.workbook.status

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Table

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = 'workbook_status')
class WorkbookStatus extends GenericEnumeratedEntity {

}
