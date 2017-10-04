package ru.tchallenge.service.complex.utility.serialization

import groovy.transform.CompileStatic

import java.time.Instant

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

import ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals

@CompileStatic
class InstantDeserializer extends JsonDeserializer<Instant> {

    @Override
    Instant deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        Foundamentals.instant(parser.valueAsString)
    }

    @Override
    Class<Instant> handledType() {
        Instant
    }
}
