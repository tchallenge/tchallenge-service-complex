package ru.tchallenge.service.complex.domain.assignment.status

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@PackageScope
@BootstrapComponent
class AssignmentStatusBootstrapBean extends GenericEnumeratedBootstrapBean<AssignmentStatus> {

    @Override
    protected Collection<AssignmentStatus> enumeratedEntities() {
        [
                new AssignmentStatus(
                        textcode: 'STANDBY',
                        title: 'Ожидает решения'
                ),
                new AssignmentStatus(
                        textcode: 'READY',
                        title: 'Готово к проверке'
                ),
                new AssignmentStatus(
                        textcode: 'ASSESSED',
                        title: 'Проверено'
                ),
                new AssignmentStatus(
                        textcode: 'DELETED',
                        title: 'Удалено'
                )
        ]
    }
}
