package ru.tchallenge.service.complex.common.ordinal

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.Bootstrap

@CompileStatic
interface GenericOrdinalBootstrap<E extends GenericOrdinalEntity> extends Bootstrap<E, Long> {

}
