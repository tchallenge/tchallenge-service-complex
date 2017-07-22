package ru.tchallenge.service.complex.domain.task.option

import groovy.transform.CompileStatic
import groovy.transform.Immutable

@CompileStatic
@Immutable
class TaskOptionInfo {

    String content
    Boolean correct
    String textcode
}
