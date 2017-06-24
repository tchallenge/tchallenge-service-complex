package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.Repository

@CompileStatic
@NoRepositoryBean
interface GenericRepository<E extends GenericEntity, ID extends Serializable> extends Repository<E, ID> {

    E findById(ID id)

    E save(E entity)
}
