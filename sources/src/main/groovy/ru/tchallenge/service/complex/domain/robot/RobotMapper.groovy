package ru.tchallenge.service.complex.domain.robot

import groovy.transform.TypeChecked

@TypeChecked
interface RobotMapper {

    RobotInfo asInfo(Robot entity)
}
