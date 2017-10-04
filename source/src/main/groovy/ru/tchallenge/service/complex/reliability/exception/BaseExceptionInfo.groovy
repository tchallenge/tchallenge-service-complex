package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals

@CompileStatic
@Immutable
final class BaseExceptionInfo implements ExceptionInfo {

    static BaseExceptionInfo of(ExceptionCategory category) {
        new BaseExceptionInfo(
                id: Foundamentals.uuid,
                category: category,
                description: category.description
        )
    }

    String id
    ExceptionCategory category
    String description
}
