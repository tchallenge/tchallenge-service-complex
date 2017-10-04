package ru.tchallenge.service.complex.domain.robot.role

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@PackageScope
@BootstrapComponent
class RobotRoleBootstrapBean extends GenericEnumeratedBootstrapBean<RobotRole> {

    @Override
    protected Collection<RobotRole> enumeratedEntities() {
        [
                new RobotRole(
                        textcode: 'CANDVIEW',
                        title: 'Просмотрщик кандидатов'
                ),
                new RobotRole(
                        textcode: 'EVENTVIEW',
                        title: 'Просмотрщик событий'
                ),
                new RobotRole(
                        textcode: 'TASKVIEW',
                        title: 'Просмотрщик задач'
                ),
                new RobotRole(
                        textcode: 'WBKVIEW',
                        title: 'Просмотрщик рабочих тетрадей'
                )
        ]
    }
}
