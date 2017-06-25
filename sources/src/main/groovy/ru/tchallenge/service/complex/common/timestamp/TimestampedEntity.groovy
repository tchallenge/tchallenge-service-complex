package ru.tchallenge.service.complex.common.timestamp

import java.time.Instant
import javax.persistence.Column

trait TimestampedEntity {

    @Column(name = "created_at")
    Instant createdAt

    @Column(name = "last_modified_at")
    Instant lastModifiedAt
}
