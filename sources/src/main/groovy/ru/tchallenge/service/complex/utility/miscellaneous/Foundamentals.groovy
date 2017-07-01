package ru.tchallenge.service.complex.utility.miscellaneous

import java.time.Instant
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.function.Function
import java.util.stream.Collectors

import groovy.transform.CompileStatic

@CompileStatic
final class Foundamentals {

    static boolean flag(Integer value) {
        return value
    }

    static Integer flag(Boolean value) {
        return value ? 1 : 0
    }

    static Instant instant(String iso) {
        return ZonedDateTime.parse(iso, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant()
    }

    static <R, T> Collection<R> mapCollection(Collection<T> collection, Function<T, R> mapper) {
        return collection
                .stream()
                .map(mapper)
                .collect(Collectors.toList())
    }

    static Instant now() {
        return Instant.now()
    }

    static String uuid() {
        return UUID.randomUUID().toString()
    }
}
