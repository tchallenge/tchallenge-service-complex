package ru.tchallenge.service.complex.common.search

import java.util.function.Function

import groovy.transform.CompileStatic

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals

@CompileStatic
final class SearchTransformations {

    static final SearchTransformations INSTANCE = new SearchTransformations()

    static Collection<Long> normalizeOrdinalIds(Collection<String> ids) {
        Foundamentals.mapCollection(ids) { String it -> it as Long }
    }

    static String normalizePattern(String pattern) {
        if (!pattern || pattern.isEmpty()) {
            return null
        }
        ("%" + pattern + "%").toUpperCase()
    }

    static Pageable pageable(GenericSearchInvoice invoice) {
        new PageRequest(invoice.pageNumber - 1, invoice.pageSize)
    }

    static <E, T> SearchInfo<T> info(GenericSearchInvoice invoice, Page<E> page, Function<E, T> mapper) {
        new SearchInfo<T>(
                pageCount: page.totalPages,
                pageNumber: invoice?.pageNumber,
                pageSize: invoice?.pageSize,
                content: Foundamentals.mapCollection(page.content, mapper)
        )
    }

    private SearchTransformations() {

    }
}
