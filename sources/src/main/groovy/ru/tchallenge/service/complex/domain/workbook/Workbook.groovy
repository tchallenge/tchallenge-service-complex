package ru.tchallenge.service.complex.domain.workbook

import javax.persistence.Entity
import javax.persistence.Table

import ru.tchallenge.service.complex.common.ordinal.GenericPrimaryEntity

@Entity
@Table(name = "workbooks")
class Workbook extends GenericPrimaryEntity {

}
