package ru.tchallenge.service.complex.common.enumerated

import java.util.stream.Collectors

import groovy.transform.CompileStatic

@CompileStatic
class EnumeratedHelper {

    static Collection<EnumeratedInfo> all(GenericEnumeratedRepository<? extends GenericEnumeratedEntity> repository) {
        return repository
                .findAll()
                .stream()
                .sorted()
                .map { GenericEnumeratedEntity entity -> one(entity) }
                .collect(Collectors.toList())
    }

    static EnumeratedInfo one(GenericEnumeratedEntity entity) {
        return new EnumeratedInfo(
                textcode: entity.textcode,
                title: entity.title,
                description: entity.description
        )
    }
}
