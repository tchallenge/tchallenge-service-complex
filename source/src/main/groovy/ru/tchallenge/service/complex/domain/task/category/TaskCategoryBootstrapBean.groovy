package ru.tchallenge.service.complex.domain.task.category

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@PackageScope
@BootstrapComponent
class TaskCategoryBootstrapBean extends GenericEnumeratedBootstrapBean<TaskCategory> {

    @Override
    protected Collection<TaskCategory> enumeratedEntities() {
        [
                new TaskCategory(
                        textcode: 'COMMON',
                        title: 'Задачи на общую логику'
                ),
                new TaskCategory(
                        textcode: 'OOD',
                        title: 'Объекто-ориентированный дизайн'
                ),
                new TaskCategory(
                        textcode: 'JAVA',
                        title: 'Java и JVM'
                ),
                new TaskCategory(
                        textcode: 'JAVASCRIPT',
                        title: 'JavaScript и ECMA'
                ),
                new TaskCategory(
                        textcode: 'TEST',
                        title: 'Тестирование ПО'
                ),
                new TaskCategory(
                        textcode: 'SQL',
                        title: 'SQL и реляционная модель'
                )
        ]
    }
}
