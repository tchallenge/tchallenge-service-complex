package ru.tchallenge.service.complex.common

import java.util.stream.Collectors

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository
import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo

@CompileStatic
abstract class GenericService {

    protected Collection<EnumeratedInfo> enumerated(GenericEnumeratedRepository<? extends GenericEnumeratedEntity> repository) {
        return repository
                .findAll()
                .stream()
                .sorted()
                .map { GenericEnumeratedEntity entity -> asEnumeratedInfo(entity) }
                .collect(Collectors.toList())
    }

    protected EnumeratedInfo asEnumeratedInfo(GenericEnumeratedEntity entity) {
        return new EnumeratedInfo(
                textcode: entity.textcode,
                title: entity.title,
                description: entity.description
        )
    }
}
