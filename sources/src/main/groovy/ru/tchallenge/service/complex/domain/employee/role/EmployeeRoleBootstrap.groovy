package ru.tchallenge.service.complex.domain.employee.role

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@BootstrapComponent
class EmployeeRoleBootstrap extends GenericEnumeratedBootstrapBean<EmployeeRole> {

    @Override
    protected Collection<EmployeeRole> enumeratedEntities() {
        return [
                new EmployeeRole(
                        textcode: "ADMIN",
                        title: "Администратор"
                ),
                new EmployeeRole(
                        textcode: "USERMOD",
                        title: "Модератор аккаунтов"
                ),
                new EmployeeRole(
                        textcode: "CANDMOD",
                        title: "Модератор кандидатов"
                ),
                new EmployeeRole(
                        textcode: "CANDVIEW",
                        title: "Просмотрщик кандидатов"
                ),
                new EmployeeRole(
                        textcode: "EVENTMOD",
                        title: "Модератор событий"
                ),
                new EmployeeRole(
                        textcode: "EVENTVIEW",
                        title: "Просмотрщик событий"
                ),
                new EmployeeRole(
                        textcode: "TASKMOD",
                        title: "Модератор задач"
                ),
                new EmployeeRole(
                        textcode: "TASKVIEW",
                        title: "Просмотрщик задач"
                ),
                new EmployeeRole(
                        textcode: "WBKVIEW",
                        title: "Просмотрщик рабочих тетрадей"
                )
        ]
    }
}
