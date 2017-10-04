package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic

@CompileStatic
interface ExceptionInfo {

    String getId()

    ExceptionCategory getCategory()

    String getDescription()
}
