package ru.tchallenge.service.complex.domain.task.input

import groovy.transform.CompileStatic
import groovy.transform.Immutable

@CompileStatic
@Immutable
class TaskInputInfo {

    String content
    Boolean regex
    Integer stance
}
