package ru.tchallenge.service.complex.domain.assignment.status

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrap
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@BootstrapComponent
class AssignmentStatusBootstrap extends GenericEnumeratedBootstrap<AssignmentStatus> {

    @Override
    protected Collection<AssignmentStatus> enumeratedEntities() {
        return [
                new AssignmentStatus(
                        textcode: "STANDBY",
                        title: "Ожидает решения"
                ),
                new AssignmentStatus(
                        textcode: "READY",
                        title: "Готово к проверке"
                ),
                new AssignmentStatus(
                        textcode: "ASSESSED",
                        title: "Проверено"
                ),
                new AssignmentStatus(
                        textcode: "DELETED",
                        title: "Удалено"
                )
        ]
    }
}
