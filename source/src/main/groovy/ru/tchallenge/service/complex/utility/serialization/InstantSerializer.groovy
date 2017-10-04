package ru.tchallenge.service.complex.utility.serialization

import groovy.transform.CompileStatic

import java.time.Instant

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider

@CompileStatic
class InstantSerializer extends JsonSerializer<Instant> {

    @Override
    void serialize(Instant value, JsonGenerator generator, SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        generator.writeString(value.toString())
    }

    @Override
    Class<Instant> handledType() {
        Instant
    }
}
