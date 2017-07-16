package ru.tchallenge.service.complex.reliability.violation

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import org.springframework.http.converter.HttpMessageNotReadableException

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.JsonMappingException

import ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals

@CompileStatic
@Immutable
final class DeserializationViolationInfo implements ViolationInfo {

    static DeserializationViolationInfo parse(HttpMessageNotReadableException exception) {
        def attribute = null
        if (exception.cause instanceof JsonMappingException) {
            def jsonException = exception.cause as JsonMappingException
            def fields = Foundamentals.mapCollection(jsonException.path) {
                JsonMappingException.Reference it -> it.fieldName
            }
            attribute = fields.join('.')
        }
        new DeserializationViolationInfo(
                base: new BaseViolationInfo(
                        category: ViolationCategory.CONTRACT,
                        description: 'Request body has not been recognized',
                        textcode: 'X.CONTRACT.REQUEST.BODY.INVALID'
                ),
                attribute: attribute
        )
    }

    @Delegate
    @JsonIgnore
    BaseViolationInfo base

    String attribute
}
