package ru.tchallenge.service.complex.domain.robot

import javax.persistence.Entity
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.ordinal.GenericSecondaryEntity

@CompileStatic
@Entity
@Table(name = "robot")
class Robot extends GenericSecondaryEntity {

}
