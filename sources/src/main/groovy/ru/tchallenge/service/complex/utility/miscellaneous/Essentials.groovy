package ru.tchallenge.service.complex.utility.miscellaneous

import java.time.Instant
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import groovy.transform.CompileStatic

@CompileStatic
trait Essentials {

    static boolean flag(Integer value) {
        return value
    }

    static Integer flag(Boolean value) {
        return value ? 1 : 0
    }

    static Instant instant(String iso) {
        return ZonedDateTime.parse(iso, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant()
    }

    static Instant now() {
        return Instant.now()
    }

    static String uuid() {
        return UUID.randomUUID().toString()
    }
}
