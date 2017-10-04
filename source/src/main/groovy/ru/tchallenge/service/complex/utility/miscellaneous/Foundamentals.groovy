package ru.tchallenge.service.complex.utility.miscellaneous

import groovy.transform.CompileStatic

import java.time.Instant
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.function.Function
import java.util.stream.Collectors

@CompileStatic
final class Foundamentals {

    public static final Foundamentals INSTANCE = new Foundamentals()

    static boolean flag(Integer value) {
        value as boolean
    }

    static Integer flag(Boolean value) {
        value ? 1 : 0
    }

    static Instant instant(String iso) {
        ZonedDateTime.parse(iso, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant()
    }

    static <R, T> Collection<R> mapCollection(Collection<T> collection, Function<T, R> mapper) {
        collection
                .stream()
                .map(mapper)
                .collect(Collectors.toList())
    }

    static Instant getNow() {
        Instant.now()
    }

    static String getUuid() {
        UUID.randomUUID().toString()
    }

    private Foundamentals() {

    }
}
