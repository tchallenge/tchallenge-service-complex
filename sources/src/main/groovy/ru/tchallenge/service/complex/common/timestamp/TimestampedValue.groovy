package ru.tchallenge.service.complex.common.timestamp

import groovy.transform.CompileStatic

import java.time.Instant

@CompileStatic
trait TimestampedValue {

    Instant createdAt
    Instant lastModifiedAt
}
