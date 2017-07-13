package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic

@CompileStatic
enum ExceptionCategory {

    UNPREDICTED("Unpredicted exception has occurred"),

    UNSUPPORTED("Requested operation is not supported"),

    VIOLATION("Request can not be fulfilled due to existing violation")

    private final String description

    ExceptionCategory(String description) {
        this.description = description
    }

    String getDescription() {
        return description
    }
}
