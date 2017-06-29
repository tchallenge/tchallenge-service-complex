package ru.tchallenge.service.complex.domain.task.snippet.style

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrap
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@BootstrapComponent
class TaskSnippetStyleBootstrap extends GenericEnumeratedBootstrap<TaskSnippetStyle> {

    @Override
    protected Collection<TaskSnippetStyle> enumeratedEntities() {
        return [
                new TaskSnippetStyle(
                        textcode: "BASIC",
                        title: "Базовый/общий"
                ),
                new TaskSnippetStyle(
                        textcode: "JAVA",
                        title: "Код на Java"
                ),
                new TaskSnippetStyle(
                        textcode: "SQL",
                        title: "SQL-запрос"
                )
        ]
    }
}
