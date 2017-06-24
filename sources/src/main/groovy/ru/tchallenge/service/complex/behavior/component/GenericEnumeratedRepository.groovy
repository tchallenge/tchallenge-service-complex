package ru.tchallenge.service.complex.behavior.component

import groovy.transform.CompileStatic

import org.springframework.data.repository.NoRepositoryBean

import ru.tchallenge.service.complex.behavior.entity.GenericEnumeratedEntity

@CompileStatic
@NoRepositoryBean
interface GenericEnumeratedRepository<E extends GenericEnumeratedEntity> extends GenericRepository<E, String> {

    Collection<E> findAll()
}
