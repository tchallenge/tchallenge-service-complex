package ru.tchallenge.service.complex.domain.task.expectation

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@PackageScope
@BootstrapComponent
class TaskExpectationBootstrapBean extends GenericEnumeratedBootstrapBean<TaskExpectation> {

    @Override
    protected Collection<TaskExpectation> enumeratedEntities() {
        [
                new TaskExpectation(
                        textcode: 'SINGLESELECT',
                        title: 'Выбор одного варианта'
                ),
                new TaskExpectation(
                        textcode: 'MULTISELECT',
                        title: 'Выбор нескольких вариантов'
                ),
                new TaskExpectation(
                        textcode: 'SMALLINPUT',
                        title: 'Короткий открытый ответ'
                ),
                new TaskExpectation(
                        textcode: 'LARGEINPUT',
                        title: 'Объемный открытый ответ'
                )
        ]
    }
}
