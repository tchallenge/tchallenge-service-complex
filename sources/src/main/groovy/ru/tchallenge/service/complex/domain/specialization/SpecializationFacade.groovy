package ru.tchallenge.service.complex.domain.specialization

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo

@CompileStatic
interface SpecializationFacade {

    Collection<EnumeratedInfo> getAll()
}
