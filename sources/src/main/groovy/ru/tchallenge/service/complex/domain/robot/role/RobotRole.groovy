package ru.tchallenge.service.complex.domain.robot.role

import javax.persistence.Entity
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = "robot_role")
class RobotRole extends GenericEnumeratedEntity {

}
