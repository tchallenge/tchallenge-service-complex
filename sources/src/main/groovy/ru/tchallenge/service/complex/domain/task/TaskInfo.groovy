package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInfoValue
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.domain.task.input.TaskInputInfo
import ru.tchallenge.service.complex.domain.task.option.TaskOptionInfo
import ru.tchallenge.service.complex.domain.task.snippet.TaskSnippetInfo

@CompileStatic
class TaskInfo extends GenericInfoValue {

    String id

    String title
    String introduction
    String question

    Collection<TaskInputInfo> inputs
    Collection<TaskOptionInfo> options
    Collection<TaskSnippetInfo> snippets

    Collection<EnumeratedInfo> categories
    EnumeratedInfo difficulty
    EnumeratedInfo expectation
    EnumeratedInfo status
}
