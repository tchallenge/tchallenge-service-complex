package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic

@CompileStatic
class NotSupportedException extends OriginatedException {

    static NotSupportedException deprecatedSince(Object origin, String version) {
        deprecatedSince(origin, version, null)
    }

    static NotSupportedException deprecatedSince(Object origin, String version, String comment) {
        new NotSupportedException(origin.class, version, null, comment)
    }

    static NotSupportedException expectedSince(Object origin, String version) {
        expectedSince(origin, version, null)
    }

    static NotSupportedException expectedSince(Object origin, String version, String comment) {
        new NotSupportedException(origin.class, null, version, comment)
    }

    private final String comment
    private final String deprecatedSince
    private final String expectedSince

    NotSupportedException(Class<?> origin, String deprecatedSince, String expectedSince, String comment) {
        super(origin)
        this.deprecatedSince = deprecatedSince
        this.expectedSince = expectedSince
        this.comment = comment
    }

    String getComment() {
        comment
    }

    String getDeprecatedSince() {
        deprecatedSince
    }

    String getExpectedSince() {
        expectedSince
    }
}
