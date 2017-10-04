package ru.tchallenge.service.complex.domain.event.status

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@PackageScope
@BootstrapComponent
class EventStatusBootstrapBean extends GenericEnumeratedBootstrapBean<EventStatus> {

    @Override
    protected Collection<EventStatus> enumeratedEntities() {
        [
                new EventStatus(
                        textcode: 'CREATED',
                        title: 'Создано'
                ),
                new EventStatus(
                        textcode: 'APPROVED',
                        title: 'Подтверждено'
                ),
                new EventStatus(
                        textcode: 'SUSPENDED',
                        title: 'Приостановлено'
                ),
                new EventStatus(
                        textcode: 'DELETED',
                        title: 'Удалено'
                )
        ]
    }
}
