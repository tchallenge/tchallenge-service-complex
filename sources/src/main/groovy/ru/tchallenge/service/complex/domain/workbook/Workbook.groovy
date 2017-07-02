package ru.tchallenge.service.complex.domain.workbook

import javax.persistence.Entity
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalEntity
import ru.tchallenge.service.complex.common.timestamp.TimestampedEntity

@CompileStatic
@Entity
@Table(name = "workbook")
class Workbook extends GenericOrdinalEntity implements TimestampedEntity {

}
