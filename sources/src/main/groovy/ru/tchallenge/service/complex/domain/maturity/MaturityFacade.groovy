package ru.tchallenge.service.complex.domain.maturity

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo

@CompileStatic
interface MaturityFacade {

    Collection<EnumeratedInfo> getAll()
}
