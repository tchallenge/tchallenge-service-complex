package ru.tchallenge.service.complex.common.enumerated

import java.util.stream.Collectors

import groovy.transform.CompileStatic

import static ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals.mapCollection

@CompileStatic
final class EnumeratedTransformations {

    static Collection<EnumeratedInfo> all(GenericEnumeratedRepository repository) {
        return repository
                .findAll()
                .stream()
                .sorted()
                .map { GenericEnumeratedEntity it -> info(it) }
                .collect(Collectors.toList())
    }

    static EnumeratedInfo info(GenericEnumeratedEntity entity) {
        return new EnumeratedInfo(
                textcode: entity.textcode,
                title: entity.title,
                description: entity.description
        )
    }

    static Collection<EnumeratedInfo> infos(Collection<? extends GenericEnumeratedEntity> entities) {
        return mapCollection(entities) { GenericEnumeratedEntity it -> info(it) }
    }

    static EnumeratedInvoice invoice(String textcode) {
        return new EnumeratedInvoice(
                textcode: textcode
        )
    }

    static Collection<EnumeratedInvoice> invoices(String... textcodes) {
        return mapCollection(textcodes.toList()) { String it -> invoice(it) }
    }

    static <E extends GenericEnumeratedEntity, R extends GenericEnumeratedRepository<E>> E one(R repository, EnumeratedInvoice invoice) {
        return one(repository, invoice.textcode)
    }

    static <E extends GenericEnumeratedEntity, R extends GenericEnumeratedRepository<E>> E one(R repository, String textcode) {
        return repository.findByTextcode(textcode)
    }

    static <E extends GenericEnumeratedEntity, R extends GenericEnumeratedRepository<E>> Collection<E> some(R repository, Collection<EnumeratedInvoice> invoices) {
        return mapCollection(invoices) { EnumeratedInvoice it -> one(repository, it) }
    }

    static <E extends GenericEnumeratedEntity, R extends GenericEnumeratedRepository<E>> Collection<E> some(R repository, String... textcodes) {
        return mapCollection(textcodes.toList()) { String it -> one(repository, it) }
    }

    private EnumeratedTransformations() {

    }
}
