package ru.tchallenge.service.complex.common.search

import java.util.function.Function
import java.util.stream.Collectors

import groovy.transform.CompileStatic

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

@CompileStatic
trait SearchAware {

    static String normalizePattern(String pattern) {
        if (!pattern || pattern.isEmpty()) {
            return null
        }
        return ("%" + pattern + "%").toUpperCase()
    }

    static Pageable pageable(GenericSearchInvoice invoice) {
        return new PageRequest(invoice.pageNumber - 1, invoice.pageSize)
    }

    static <E, T> SearchInfo<T> searchInfo(GenericSearchInvoice invoice, Page<E> page, Function<E, T> mapper) {
        return new SearchInfo<T>(
                pageCount: page.totalPages,
                pageNumber: invoice?.pageNumber,
                pageSize: invoice?.pageSize,
                content: page
                        .content
                        .stream()
                        .map(mapper)
                        .collect(Collectors.toList()))
    }
}
