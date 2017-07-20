package ru.tchallenge.service.complex.domain.robot

import groovy.transform.CompileStatic

@CompileStatic
interface RobotMapper {

    Robot asEntity(Robot entity, RobotInvoice invoice)

    RobotInfo asInfo(Robot entity)
}
