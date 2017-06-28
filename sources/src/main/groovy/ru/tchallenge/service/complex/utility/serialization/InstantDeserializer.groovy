package ru.tchallenge.service.complex.utility.serialization

import java.time.Instant
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import groovy.transform.CompileStatic

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

@CompileStatic
class InstantDeserializer extends JsonDeserializer<Instant> {

    static Instant parse(String iso) {
        return ZonedDateTime.parse(iso, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant()
    }

    @Override
    Instant deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        parse(parser.valueAsString)
    }

    @Override
    Class<Instant> handledType() {
        return Instant
    }
}
