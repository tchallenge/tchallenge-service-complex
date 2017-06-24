package ru.tchallenge.service.complex.common.complementary

import groovy.transform.CompileStatic

import org.springframework.data.repository.NoRepositoryBean

import ru.tchallenge.service.complex.common.GenericRepository

@CompileStatic
@NoRepositoryBean
interface GenericComplementaryRepository<E extends GenericComplementaryEntity> extends GenericRepository<E, String> {

}
