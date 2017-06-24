package ru.tchallenge.service.complex.common.search

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericInfoValue

@CompileStatic
class SearchInfo<T> extends GenericInfoValue {

    Collection<T> items
    Long offset
    Long total
}
