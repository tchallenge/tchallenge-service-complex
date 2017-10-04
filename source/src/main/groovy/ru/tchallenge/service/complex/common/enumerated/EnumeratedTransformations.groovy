package ru.tchallenge.service.complex.common.enumerated

import groovy.transform.CompileStatic

import java.util.stream.Collectors

import ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals

@CompileStatic
final class EnumeratedTransformations {

    static final EnumeratedTransformations INSTANCE = new EnumeratedTransformations()

    static Collection<EnumeratedInfo> all(GenericEnumeratedRepository repository) {
        repository
                .findAll()
                .stream()
                .sorted()
                .map { GenericEnumeratedEntity it -> info(it) }
                .collect(Collectors.toList())
    }

    static EnumeratedInfo info(GenericEnumeratedEntity entity) {
        new EnumeratedInfo(
                textcode: entity.textcode,
                title: entity.title,
                description: entity.description
        )
    }

    static Collection<EnumeratedInfo> infos(Collection<? extends GenericEnumeratedEntity> entities) {
        Foundamentals.mapCollection(entities) { GenericEnumeratedEntity it -> info(it) }
    }

    static EnumeratedInvoice invoice(String textcode) {
        new EnumeratedInvoice(
                textcode: textcode
        )
    }

    static Collection<EnumeratedInvoice> invoices(String... textcodes) {
        Foundamentals.mapCollection(textcodes.toList()) { String it -> invoice(it) }
    }

    static <E extends GenericEnumeratedEntity, R extends GenericEnumeratedRepository<E>> E one(R repository, EnumeratedInvoice invoice) {
        one(repository, invoice.textcode)
    }

    static <E extends GenericEnumeratedEntity, R extends GenericEnumeratedRepository<E>> E one(R repository, String textcode) {
        repository.findByTextcode(textcode)
    }

    static <E extends GenericEnumeratedEntity, R extends GenericEnumeratedRepository<E>> Collection<E> some(R repository, Collection<EnumeratedInvoice> invoices) {
        Foundamentals.mapCollection(invoices) { EnumeratedInvoice it -> one(repository, it) }
    }

    static <E extends GenericEnumeratedEntity, R extends GenericEnumeratedRepository<E>> Collection<E> some(R repository, String... textcodes) {
        Foundamentals.mapCollection(textcodes.toList()) { String it -> one(repository, it) }
    }

    private EnumeratedTransformations() {

    }
}
