package ru.tchallenge.service.complex.domain.workbook.status

import javax.persistence.Entity
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.behavior.entity.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = "account_status")
class WorkbookStatus extends GenericEnumeratedEntity {

}
