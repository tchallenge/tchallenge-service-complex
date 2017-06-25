package ru.tchallenge.service.complex.common.timestamp

import java.time.Instant

import groovy.transform.CompileStatic

@CompileStatic
trait TimestampedValue {

    Instant createdAt
    Instant lastModifiedAt
}
