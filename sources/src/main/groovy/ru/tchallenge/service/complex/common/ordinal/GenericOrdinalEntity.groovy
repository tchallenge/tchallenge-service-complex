package ru.tchallenge.service.complex.common.ordinal

import javax.persistence.MappedSuperclass

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericEntity

@CompileStatic
@MappedSuperclass
abstract class GenericOrdinalEntity extends GenericEntity<Long> {

}
