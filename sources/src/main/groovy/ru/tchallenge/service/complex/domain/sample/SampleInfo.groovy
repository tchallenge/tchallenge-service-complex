package ru.tchallenge.service.complex.domain.sample

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import java.time.Instant

@CompileStatic
@Immutable(knownImmutableClasses = [Instant])
class SampleInfo {

    String id
    String title
    String description
    Instant createdAt
    Instant lastModifiedAt
}
