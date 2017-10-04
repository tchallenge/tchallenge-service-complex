package ru.tchallenge.service.complex.common.ordinal.sequence

import groovy.transform.CompileStatic

@CompileStatic
interface OrdinalSequenceService {

    Long nextValue(String id)
}
