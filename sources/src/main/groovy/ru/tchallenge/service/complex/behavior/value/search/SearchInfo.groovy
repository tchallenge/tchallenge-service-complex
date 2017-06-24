package ru.tchallenge.service.complex.behavior.value.search

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.behavior.value.GenericInfoValue

@CompileStatic
class SearchInfo<T> extends GenericInfoValue {

    Collection<T> items
    Long offset
    Long total
}
