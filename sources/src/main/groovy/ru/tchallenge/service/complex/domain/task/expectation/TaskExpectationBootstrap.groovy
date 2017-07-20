package ru.tchallenge.service.complex.domain.task.expectation

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@BootstrapComponent
class TaskExpectationBootstrap extends GenericEnumeratedBootstrapBean<TaskExpectation> {

    @Override
    protected Collection<TaskExpectation> enumeratedEntities() {
        return [
                new TaskExpectation(
                        textcode: "SINGLESELECT",
                        title: "Выбор одного варианта"
                ),
                new TaskExpectation(
                        textcode: "MULTISELECT",
                        title: "Выбор нескольких вариантов"
                ),
                new TaskExpectation(
                        textcode: "SMALLINPUT",
                        title: "Короткий открытый ответ"
                ),
                new TaskExpectation(
                        textcode: "LARGEINPUT",
                        title: "Объемный открытый ответ"
                )
        ]
    }
}
