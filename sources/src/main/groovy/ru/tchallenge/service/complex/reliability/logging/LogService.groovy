package ru.tchallenge.service.complex.reliability.logging

import groovy.transform.CompileStatic

@CompileStatic
interface LogService {

    void log(LogRecord record)
}
