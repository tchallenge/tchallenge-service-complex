package ru.tchallenge.service.complex.domain.workbook

import javax.persistence.Entity
import javax.persistence.Table

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalEntity
import ru.tchallenge.service.complex.common.timestamp.TimestampedEntity

@Entity
@Table(name = "workbooks")
class Workbook extends GenericOrdinalEntity implements TimestampedEntity {

}
