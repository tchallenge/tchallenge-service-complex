package ru.tchallenge.service.complex.domain.task.snippet.style

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@PackageScope
@BootstrapComponent
class TaskSnippetStyleBootstrapBean extends GenericEnumeratedBootstrapBean<TaskSnippetStyle> {

    @Override
    protected Collection<TaskSnippetStyle> enumeratedEntities() {
        [
                new TaskSnippetStyle(
                        textcode: 'BASIC',
                        title: 'Базовый/общий'
                ),
                new TaskSnippetStyle(
                        textcode: 'JAVA',
                        title: 'Код на Java'
                ),
                new TaskSnippetStyle(
                        textcode: 'SQL',
                        title: 'SQL-запрос'
                )
        ]
    }
}
