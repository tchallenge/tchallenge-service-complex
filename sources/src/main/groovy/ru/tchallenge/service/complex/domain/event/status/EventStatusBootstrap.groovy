package ru.tchallenge.service.complex.domain.event.status

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrap
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@BootstrapComponent
class EventStatusBootstrap extends GenericEnumeratedBootstrap<EventStatus> {

    @Override
    protected Collection<EventStatus> enumeratedEntities() {
        return [
                new EventStatus(
                        textcode: "CREATED",
                        title: "Создано"
                ),
                new EventStatus(
                        textcode: "APPROVED",
                        title: "Подтверждено"
                ),
                new EventStatus(
                        textcode: "SUSPENDED",
                        title: "Приостановлено"
                ),
                new EventStatus(
                        textcode: "DELETED",
                        title: "Удалено"
                )
        ]
    }
}
