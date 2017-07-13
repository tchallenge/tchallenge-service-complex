package ru.tchallenge.service.complex.reliability.logging

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import org.springframework.boot.logging.LogLevel

@CompileStatic
@Immutable(knownImmutableClasses = [Object, Throwable])
final class LogRecord {

    String descriptor
    LogLevel level
    Object message
    Throwable throwable
}
