package ru.tchallenge.service.complex.common.timestamp

import java.time.Instant

trait TimestampedValue {

    Instant createdAt
    Instant lastModifiedAt
}
