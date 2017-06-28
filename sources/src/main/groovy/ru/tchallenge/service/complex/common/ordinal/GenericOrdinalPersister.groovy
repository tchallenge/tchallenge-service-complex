package ru.tchallenge.service.complex.common.ordinal

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericPersister
import ru.tchallenge.service.complex.common.ordinal.sequence.OrdinalSequenceService

@CompileStatic
abstract class GenericOrdinalPersister<E extends GenericOrdinalEntity> extends GenericPersister<E, Long> {

    @Autowired
    protected OrdinalSequenceService ordinalSequenceService

    protected Long nextOrdinalSequence(String sequenceId) {
        return ordinalSequenceService.nextValue(sequenceId)
    }
}
