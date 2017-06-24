package ru.tchallenge.service.complex.common.enumerated

import groovy.transform.CompileStatic

import org.springframework.data.repository.NoRepositoryBean

import ru.tchallenge.service.complex.common.GenericRepository

@CompileStatic
@NoRepositoryBean
interface GenericEnumeratedRepository<E extends GenericEnumeratedEntity> extends GenericRepository<E, String> {

    Collection<E> findAll()
}
