package ru.tchallenge.service.complex.behavior.value.search

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.behavior.value.InfoValue

@CompileStatic
class SearchInfo<T> extends InfoValue {

    Collection<T> items
    Long offset
    Long total
}
