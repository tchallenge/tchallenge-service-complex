package ru.tchallenge.service.complex.domain.maturity

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.common.enumerated.EnumeratedHelper
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.search.SearchAware
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@ServiceComponent
class MaturityService extends GenericService implements SearchAware {

    @Autowired
    protected MaturityRepository maturityRepository

    Collection<EnumeratedInfo> getAll() {
        return EnumeratedHelper.all(maturityRepository)
    }
}
