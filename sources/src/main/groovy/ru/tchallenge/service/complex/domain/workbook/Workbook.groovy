package ru.tchallenge.service.complex.domain.workbook

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalEntity
import ru.tchallenge.service.complex.common.timestamp.TimestampedEntity
import ru.tchallenge.service.complex.domain.assignment.Assignment

@CompileStatic
@Entity
@Table(name = "workbook")
class Workbook extends GenericOrdinalEntity implements TimestampedEntity {

    @OneToMany(mappedBy = "workbook", cascade = CascadeType.ALL)
    Collection<Assignment> assignments = []
}
