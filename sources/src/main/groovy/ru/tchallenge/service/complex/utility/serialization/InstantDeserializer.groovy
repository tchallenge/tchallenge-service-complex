package ru.tchallenge.service.complex.utility.serialization

import java.time.Instant

import groovy.transform.CompileStatic

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

import static ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals.instant

@CompileStatic
class InstantDeserializer extends JsonDeserializer<Instant> {

    @Override
    Instant deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        instant(parser.valueAsString)
    }

    @Override
    Class<Instant> handledType() {
        return Instant
    }
}
