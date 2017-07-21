package ru.tchallenge.service.complex.common.ordinal

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericBootstrap

@CompileStatic
interface GenericOrdinalBootstrap<E extends GenericOrdinalEntity> extends GenericBootstrap<E, Long> {

}
