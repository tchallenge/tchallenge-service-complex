package ru.tchallenge.service.complex.common.ordinal

import groovy.transform.CompileStatic

import org.springframework.data.repository.NoRepositoryBean

import ru.tchallenge.service.complex.common.GenericRepository

@CompileStatic
@NoRepositoryBean
interface GenericOrdinalRepository<E extends GenericOrdinalEntity> extends GenericRepository<E, Long> {

}
