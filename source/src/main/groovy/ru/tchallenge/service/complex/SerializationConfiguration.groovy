package ru.tchallenge.service.complex

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper

import ru.tchallenge.service.complex.utility.serialization.InstantDeserializer
import ru.tchallenge.service.complex.utility.serialization.InstantSerializer

@CompileStatic
@PackageScope
@Configuration
class SerializationConfiguration {

    @Bean
    ObjectMapper jacksonObjectMapper() {
        new Jackson2ObjectMapperBuilder()
                .failOnEmptyBeans(false)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .serializationInclusion(JsonInclude.Include.NON_EMPTY)
                .serializers(instantSerializer())
                .deserializers(instantDeserializer())
                .build()
    }

    @Bean
    InstantSerializer instantSerializer() {
        new InstantSerializer()
    }

    @Bean
    InstantDeserializer instantDeserializer() {
        new InstantDeserializer()
    }
}
