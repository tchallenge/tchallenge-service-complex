package ru.tchallenge.service.complex.domain.task.status

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrap
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@BootstrapComponent
class TaskStatusBootstrap extends GenericEnumeratedBootstrap<TaskStatus> {

    @Override
    protected Collection<TaskStatus> enumeratedEntities() {
        return [
                new TaskStatus(
                        textcode: "CREATED",
                        title: "Создана"
                ),
                new TaskStatus(
                        textcode: "APPROVED",
                        title: "Подтверждена"
                ),
                new TaskStatus(
                        textcode: "BLACKLISTED",
                        title: "Добавлена в черный список"
                ),
                new TaskStatus(
                        textcode: "DELETED",
                        title: "Удалена"
                )
        ]
    }
}
