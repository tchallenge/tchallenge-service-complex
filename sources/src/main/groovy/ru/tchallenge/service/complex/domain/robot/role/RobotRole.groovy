package ru.tchallenge.service.complex.domain.robot.role

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Table

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = 'robot_role')
class RobotRole extends GenericEnumeratedEntity {

}
