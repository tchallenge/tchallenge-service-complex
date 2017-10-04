package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalPersisterBean
import ru.tchallenge.service.complex.convention.component.PersisterComponent
import ru.tchallenge.service.complex.domain.task.input.TaskInput
import ru.tchallenge.service.complex.domain.task.option.TaskOption
import ru.tchallenge.service.complex.domain.task.snippet.TaskSnippet

@CompileStatic
@PackageScope
@PersisterComponent
class TaskPersisterBean extends GenericOrdinalPersisterBean<Task> implements TaskPersister {

    @Override
    protected void prepare(Task entity) {
        if (!entity.id) {
            entity.id = nextOrdinalSequence('domain.task')
        }
        if (entity.inputs) {
            entity.inputs.forEach { TaskInput it -> it.task = entity }
        }
        if (entity.options) {
            entity.options.forEach { TaskOption it -> it.task = entity }
        }
        if (entity.snippets) {
            entity.snippets.forEach { TaskSnippet it -> it.task = entity }
        }
    }
}
