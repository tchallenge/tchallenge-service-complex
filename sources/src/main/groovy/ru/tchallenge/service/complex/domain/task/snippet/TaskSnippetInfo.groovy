package ru.tchallenge.service.complex.domain.task.snippet

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo

@CompileStatic
@Immutable
class TaskSnippetInfo {

    String content
    Integer stance
    EnumeratedInfo style
}
