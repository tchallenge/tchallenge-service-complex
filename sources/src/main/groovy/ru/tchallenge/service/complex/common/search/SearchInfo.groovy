package ru.tchallenge.service.complex.common.search

import groovy.transform.CompileStatic

@CompileStatic
class SearchInfo<T> {

    Collection<T> content
    Integer pageCount
    Integer pageNumber
    Integer pageSize
}
