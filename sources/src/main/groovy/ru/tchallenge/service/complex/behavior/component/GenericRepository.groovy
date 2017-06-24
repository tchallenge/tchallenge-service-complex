package ru.tchallenge.service.complex.behavior.component

import groovy.transform.CompileStatic

import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.Repository

import ru.tchallenge.service.complex.behavior.entity.GenericEntity

@CompileStatic
@NoRepositoryBean
interface GenericRepository<E extends GenericEntity<ID>, ID extends Serializable> extends Repository<E, ID> {

    E findById(ID id)

    E save(E entity)
}
