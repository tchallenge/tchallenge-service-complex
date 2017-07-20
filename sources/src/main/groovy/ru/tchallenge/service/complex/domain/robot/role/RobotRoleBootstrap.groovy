package ru.tchallenge.service.complex.domain.robot.role

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@BootstrapComponent
class RobotRoleBootstrap extends GenericEnumeratedBootstrapBean<RobotRole> {

    @Override
    protected Collection<RobotRole> enumeratedEntities() {
        return [
                new RobotRole(
                        textcode: "CANDVIEW",
                        title: "Просмотрщик кандидатов"
                ),
                new RobotRole(
                        textcode: "EVENTVIEW",
                        title: "Просмотрщик событий"
                ),
                new RobotRole(
                        textcode: "TASKVIEW",
                        title: "Просмотрщик задач"
                ),
                new RobotRole(
                        textcode: "WBKVIEW",
                        title: "Просмотрщик рабочих тетрадей"
                )
        ]
    }
}
