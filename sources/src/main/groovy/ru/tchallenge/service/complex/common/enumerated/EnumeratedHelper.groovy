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

    static Collection<EnumeratedInfo> many(Collection<? extends GenericEnumeratedEntity> entities) {
        return entities
                .stream()
                .map { GenericEnumeratedEntity entity -> one(entity) }
                .collect(Collectors.toList())
    }

    static <E extends GenericEnumeratedEntity> Collection<E> many(Collection<EnumeratedInvoice> invoices,
                                                                  GenericEnumeratedRepository<E> repository) {
        return invoices
                .stream()
                .map { EnumeratedInvoice invoice -> repository.findByTextcode(invoice.textcode) }
                .collect(Collectors.toList())
    }

    static EnumeratedInfo one(GenericEnumeratedEntity entity) {
        return new EnumeratedInfo(
                textcode: entity.textcode,
                title: entity.title,
                description: entity.description
        )
    }

    static <E extends GenericEnumeratedEntity> E one(EnumeratedInvoice invoice,
                                                     GenericEnumeratedRepository<E> repository) {
        return repository.findByTextcode(invoice.textcode)
    }
}
