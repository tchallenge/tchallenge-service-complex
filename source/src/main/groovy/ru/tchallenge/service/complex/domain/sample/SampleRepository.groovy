package ru.tchallenge.service.complex.domain.sample

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericRepository

@CompileStatic
interface SampleRepository extends GenericRepository<Sample, String> {

}
